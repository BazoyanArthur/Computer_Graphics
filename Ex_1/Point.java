public class Point
{
    public double x;
    public double y;
    public double z;

    // Constructors
    Point(double X, double Y)
    {
        setLocation(X,Y,0);
    }

    Point(int X, int Y)
    {
        setLocation(X,Y,0);
    }

    Point(double X, double Y, double Z)
    {
        setLocation(X,Y,Z);
    }

    Point(int X, int Y, int Z)
    {
        setLocation(X,Y,Z);
    }

    Point(Point p)
    {
        this.x = p.getX();
        this.y = p.getY();
        this.z = p.getZ();
    }

    public void setLocation(int X, int Y)
    {
        setLocation(X, Y, (int)z);
    }

    public void setLocation(double X, double Y)
    {
        setLocation(X, Y, z);
    }

    public void setLocation(int X, int Y, int Z)
    {
        setX(X);
        setY(Y);
        setZ(Z);
    }

    public void setLocation(double X, double Y, double Z)
    {
        setX(X);
        setY(Y);
        setZ(Z);
    }

    // utils
    public void setX(int X)
    {
        this.x = X;
    }
    public void setX(double X)
    {
        this.x = X;
    }
    public void setY(int Y)
    {
        this.y = Y;
    }
    public void setY(double Y)
    {
        this.y = Y;
    }
    public void setZ(int Z)
    {
        this.z = Z;
    }
    public void setZ(double Z)
    {
        this.z = Z;
    }

    public int getiX()
    {
        return (int)x;
    }

    public int getiY()
    {
        return (int)y;
    }

    public int getiZ()
    {
        return (int)z;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }
}
