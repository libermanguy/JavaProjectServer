/*
 * 
 */
package general;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
//TODO: Auto-generated Javadoc
/**
* 
*  * <h1>Position</h1>
* Specific Position in our game
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
public class Position implements Serializable
{

/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The _z. */
	//3 Dimension
	int _x,_y,_z;

/**
 * Instantiates a new position.
 *
 * @param x the x
 * @param y the y
 * @param z the z
 */
//Constructors 
public Position(int x, int y, int z)  
	{
		this._x = x;
		this._y = y;
		this._z = z;
	}

/**
 * Instantiates a new position.
 *
 * @param pstr the pstr
 */
public Position(String pstr) 
{
	pstr = pstr.replaceAll("[{}]","");
	String val[] = pstr.split(",");
	this._x = Integer.parseInt(val[0]);
	this._y = Integer.parseInt(val[1]);
	this._z = Integer.parseInt(val[2]);
}

/**
 * Instantiates a new position.
 *
 * @param p the p
 */
public Position(Position p) {

	this._x = p._x;
	this._y = p._y;
	this._z = p._z;
}

/**
 * Gets the x.
 *
 * @return the x
 */
//Setters & Getters
public int getX() {
	return _x;
}

/**
 * Sets the x.
 *
 * @param x the new x
 */
public void setX(int x) {
	this._x = x;
}

/**
 * Gets the y.
 *
 * @return the y
 */
public int getY() {
	return _y;
}

/**
 * Sets the y.
 *
 * @param y the new y
 */
public void setY(int y) {
	this._y = y;
}

/**
 * Gets the z.
 *
 * @return the z
 */
public int getZ() {
	return _z;
}

/**
 * Sets the z.
 *
 * @param z the new z
 */
public void setZ(int z) {
	this._z = z;
}

//Valuable function


/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override

public String toString() 
	{
		return "{" + _x + "," + _y + "," + _z + "}";
	}

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) 
	{
		Position other = (Position) obj;
		if (_x != other._x)
			return false;
		if (_y != other._y)
			return false;
		if (_z != other._z)
			return false;
		return true;
	}

/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
public int hashCode() 
{
    return  this.toString().hashCode();
}


}
