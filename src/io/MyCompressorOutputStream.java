package io;

import java.io.IOException;
import java.io.OutputStream;

// TODO: Auto-generated Javadoc
/**
* 
*  * <h1>MyCompressorOutputStream</h1>
* Stream that compress the maze 3D
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
public class MyCompressorOutputStream extends OutputStream {

	/** The _out. */
	OutputStream _out;
	
	/** The _out byte. */
	int _outByte;
	
	/** The _out count. */
	int _outCount;
	
	/** The _opened. */
	static boolean _opened = false;
	
	/**
	 * Instantiates a new my compressor output stream.
	 *
	 * @param out the out
	 */
	public MyCompressorOutputStream(OutputStream out) {
		super();
		this._out = out;
	}


	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException {
		if (!_opened)
		{
			_outByte = b;
			_outCount = 1;
			_opened = true;
		}
		else
		{
			if (b == _outByte)
				_outCount++;
			else
				{
			//		System.out.print("Char out " + _outByte);
					_out.write(_outByte);
			//		System.out.println(" Number out " + _outCount);
					_out.write(_outCount);
					_outByte=b;
					_outCount=1;
				}
		}

	}
	
	/* (non-Javadoc)
	 * @see java.io.OutputStream#close()
	 */
	@Override
	public void close() throws IOException {
//		System.out.print("Char out " + _outByte);
		_out.write(_outByte);
	//	System.out.println(" Number out " + _outCount);
		_out.write(_outCount);
		super.close();
	}

}
