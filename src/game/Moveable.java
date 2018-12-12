package game;

public interface Moveable
{
     void setPos( int x, int y);
     void setX( int x );
     void setY( int y );

     int getX();
     int getY();

	  int getWidth();
	  int getHeight();
	  void setWidth( int w );
	  void setHeight( int h );

     void setSpeed( int s );
	  int getSpeed();
}