import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Deque;

public class Drawing
{
    public static Frame frame;
    public static Graphics graphics;
    public static BufferedImage img;

    Drawing()
    {
        frame = new Frame("Raster");  //window
        final int imageWidth = 500;
        final int imageHeight = 500;
        frame.setSize(imageWidth,imageHeight);
        frame.setLocation(100,100);
        frame.setVisible(true);

        graphics = frame.getGraphics();

        img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_BYTE_GRAY);
        // clear
        gradientSetRaster(img);
        graphics.drawImage(img, 0, 0, (img1, infoflags, x, y, width, height) -> false);  //draw the image. You can think of this as the display method.
    }

    public static void drawMess(Deque<Point> points)
    {
        int[] pixel = {255,255,255};
        int size = points.size();
        if(size%2 != 0)
        {
            size -= 1;
            Point temp_point = points.removeFirst();
            int x = temp_point.getiX();
            int y = temp_point.getiY();
            if( (x >= 0 && y >= 0) && (x < frame.getWidth() && y < frame.getHeight()) )
                img.getRaster().setPixel(x,y,pixel);
            points.addLast(temp_point);
        }
        gradientSetRaster(img);
        for(int i = 0; i < size; i += 2 )
        {

            Point point1 = points.removeFirst();
            Point point2 = points.removeFirst();
            drawLine(point1, point2);
            points.addLast(point1);
            points.addLast(point2);
        }
    }

    public static void drawLine(Point point1, Point point2)
    {
        int[] pixel = {255,255,255};
        ArrayList<Point> line = new ArrayList<>();
        line = Bresenham_Line(point1, point2);
        for(int i = 0; i < line.size(); i++)
        {
            img.getRaster().setPixel(line.get(i).getiX(),line.get(i).getiY(),pixel);
        }
        graphics.drawImage(img, 0, 0, (img1, infoflags, x, y, width, height) -> false);
    }

    public static void drawObject(Deque<Point> points)
    {
        Polygon vertex = new Polygon(points);
        ArrayList<Point> ordered_point = vertex.getFace_list();
        int size = ordered_point.size();
        gradientSetRaster(img);
        for(int i = 0; i < size-1; i++)
        {
            drawLine(ordered_point.get(i), ordered_point.get(i+1));
        }
        drawLine(ordered_point.get(0), ordered_point.get(size-1));
    }

    // helper functions

    private static BufferedImage gradientSetRaster(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();

        WritableRaster raster = img.getRaster();
        int[] pixel = new int[3]; //RGB

        for (int y = 0; y < height; y++) {
            int val = (int) (y * 255f / height);
            for (int shift = 1; shift < 3; shift++) {
                pixel[shift] = val;
            }

            for (int x = 0; x < width; x++) {
                raster.setPixel(x, y, pixel);
            }
        }

        return img;
    }

    public static ArrayList<Point> Bresenham_Line( Point point1, Point point2 )
    {
        boolean swap;
        int x = (int)point1.getX();
        int y = (int)point1.getY();
        int dx = (int)Math.abs(point2.getX() - point1.getX());
        int dy = (int)Math.abs(point2.getY() - point1.getY());
        int s1 = (int)Math.signum(point2.getX() - point1.getX());
        int s2 = (int)Math.signum(point2.getY() - point1.getY());

        ArrayList<Point> points = new ArrayList<>();

        if (dy > dx)
        {
            int temp = dx;
            dx = dy;
            dy = temp;
            swap = true;
        }
        else
        {
            swap = false;
        }

        int d_start = 2 * dy - dx;
        int d_east = 2 * dy;
        int d_northEast = 2 * (dy - dx);
        // Points' locations calculation
        for (int i = 0; i < dx; ++i)
        {
            if (d_start < 0)
            {
                if (swap)
                {
                    y = y + s2;
                }
                else
                {
                    x += s1;
                }
                d_start += d_east;
            }
            else
            {
                y += s2;
                x += s1;
                d_start += d_northEast;
            }
            if( (x >= 0 && y >= 0) && (x < frame.getWidth() && y < frame.getHeight()) )
                points.add(new Point(x,y));
        }
        return points;
    }

}
