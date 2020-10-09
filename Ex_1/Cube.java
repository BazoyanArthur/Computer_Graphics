import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Cube
{
    private ArrayList<Polygon> object;
    Point center;
    double size;

    Cube( double s, Point c )
    {
        object = new ArrayList<>();
        size = s;
        center = c;
        createCube();
    }

    private void createCube()
    {
        double half_dist = size / 2;

        // Face 1

        Point point_1 = new Point(center.getX() - half_dist, center.getY() - half_dist, center.getZ() + half_dist);
        Point point_2 = new Point(center.getX() - half_dist, center.getY() - half_dist, center.getZ() - half_dist);
        Point point_3 = new Point(center.getX() - half_dist, center.getY() + half_dist, center.getZ() + half_dist);
        Point point_4 = new Point(center.getX() - half_dist, center.getY() + half_dist, center.getZ() - half_dist);
        Point point_5 = new Point(center.getX() + half_dist, center.getY() - half_dist, center.getZ() + half_dist);
        Point point_6 = new Point(center.getX() + half_dist, center.getY() - half_dist, center.getZ() - half_dist);
        Point point_7 = new Point(center.getX() + half_dist, center.getY() + half_dist, center.getZ() + half_dist);
        Point point_8 = new Point(center.getX() + half_dist, center.getY() + half_dist, center.getZ() - half_dist);

        Deque<Point> face_1 = new LinkedList<>();
        Deque<Point> face_2 = new LinkedList<>();
        Deque<Point> face_3 = new LinkedList<>();
        Deque<Point> face_4 = new LinkedList<>();
        Deque<Point> face_5 = new LinkedList<>();
        Deque<Point> face_6 = new LinkedList<>();

        face_1.addLast(point_1);
        face_1.addLast(point_2);
        face_1.addLast(point_3);
        face_1.addLast(point_4);

        face_2.addLast(point_5);
        face_2.addLast(point_6);
        face_2.addLast(point_7);
        face_2.addLast(point_8);

        face_3.addLast(point_2);
        face_3.addLast(point_4);
        face_3.addLast(point_6);
        face_3.addLast(point_8);

        face_4.addLast(point_1);
        face_4.addLast(point_3);
        face_4.addLast(point_5);
        face_4.addLast(point_7);

        face_5.addLast(point_3);
        face_5.addLast(point_4);
        face_5.addLast(point_7);
        face_5.addLast(point_8);

        face_6.addLast(point_1);
        face_6.addLast(point_2);
        face_6.addLast(point_5);
        face_6.addLast(point_6);

        object.add(new Polygon(face_1));
        object.add(new Polygon(face_2));
        object.add(new Polygon(face_3));
        object.add(new Polygon(face_4));
        object.add(new Polygon(face_5));
        object.add(new Polygon(face_6));
    }

    public void drawCube(Drawing d)
    {
        int size = object.size();
        Action.project(object.get(0).get_coordinates());
        Action.project(object.get(1).get_coordinates());
    }

}
