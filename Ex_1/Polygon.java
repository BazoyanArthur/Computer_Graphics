import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Polygon
{
    // list to keep the vertices inside
    private ArrayList<Numerical_Matrix> coordinates;
    private Deque<Point> face;

    public Polygon(Deque<Point> points)
    {
        face = new LinkedList<>();
        coordinates = new ArrayList<>();
        order(points);
        set_coordinates();
    }

    private void order(Deque<Point> points)
    {
        int size = points.size();
        ArrayList<Point> temp_list = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            temp_list.add(points.removeFirst());
            points.addLast(temp_list.get(i));
        }

        // sort by priority
        for (int i = 0; i < size-1; i++)
        {
            for (int j = 0; j < size - i - 1; j++)
            {
                if (temp_list.get(j).getiX() > temp_list.get(j + 1).getiX())
                {
                    // swap arr[j+1] and arr[j] with X
                    Point temp = temp_list.get(j);
                    temp_list.set(j,temp_list.get(j + 1));
                    temp_list.set(j + 1, temp);
                }
                else if(temp_list.get(j).getiX() == temp_list.get(j + 1).getiX())
                {
                    boolean test = false;
                    if(j < (size / 2) )
                    {
                        if (temp_list.get(j).getiY() < temp_list.get(j + 1).getiY())
                        {
                            test = true;
                        }
                    }
                    else
                    {
                        if (temp_list.get(j).getiY() > temp_list.get(j + 1).getiY())
                        {
                            test = true;
                        }
                    }

                    if (test)
                    {
                        // swap arr[j+1] and arr[j] with Y
                        Point temp = temp_list.get(j);
                        temp_list.set(j, temp_list.get(j + 1));
                        temp_list.set(j + 1, temp);
                    }
                }

            }
        }
        for(int i = 0; i < size; i++)
        {
            face.addLast( temp_list.get(i) );
        }
    }

    public void set_coordinates()
    {
        int size = face.size();
        for(int i = 0; i < size; i++)
        {
            int w = 1;
            Point temp_point = face.removeFirst();
            Numerical_Matrix temp_matrix = new Numerical_Matrix(4,1);
            temp_matrix.set_value(0,0, temp_point.getX());
            temp_matrix.set_value(1, 0, temp_point.getY());
            temp_matrix.set_value(2, 0, temp_point.getiZ());
            temp_matrix.set_value(3, 0, w);
            coordinates.add(temp_matrix);
            face.addLast(temp_point);
        }
    }

    public ArrayList<Numerical_Matrix> get_coordinates()
    {
        return coordinates;
    }

    public Deque<Point> getFace()
    {
        return face;
    }

    public ArrayList<Point> getFace_list()
    {
        int size = face.size();
        ArrayList<Point> temp_list = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            temp_list.add(face.removeFirst());
            face.addLast(temp_list.get(i));
        }
        return temp_list;
    }

    void drawPolygon(Drawing d)
    {
           Drawing.drawObject(face);
    }
}
