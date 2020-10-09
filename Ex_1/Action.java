import java.util.ArrayList;
import java.util.Deque;

public class Action
{
    private Action() { }

    public static void move(Deque<Point> points, int tx, int ty )
    {
        if(!check(points))
        {
            return;
        }
        // create identity matrix
        Numerical_Matrix matrix = new Numerical_Matrix(3);

        matrix.set_value(0,2, tx );
        matrix.set_value(1,2, ty );

        // process the points
        do_move(points, matrix);

        // draw the object
        Drawing.drawMess(points);
    }
    public static void do_move(Deque<Point> points, Numerical_Matrix matrix )
    {
        int size = points.size();
        Numerical_Matrix coordinate = new Numerical_Matrix(3,1,1);

        // loop for processing every single point there is
        for(int i = 0; i < size; i++ )
        {
            Numerical_Matrix temp_mat = matrix.copyOf();
            Point temp_point = points.removeFirst();
            // set the vector
            coordinate.set_value(0, 0, temp_point.x );
            coordinate.set_value(1, 0, temp_point.y );
            coordinate.set_value(2, 0, 1 );

            // multiply with the matrix
            temp_mat.multiply(coordinate);
            // save back the results
            temp_point.setLocation(temp_mat.get_matrix()[0][0], temp_mat.get_matrix()[1][0]);

            // place it back from the end
            points.addLast(temp_point);
        }
    }
    /* Moving */

    /* Rotation */
    public static void rotate(Deque<Point> points, double theta)
    {
        if(!check(points))
        {
            return;
        }
        do_rotate(points, theta);
        Drawing.drawMess(points);
    }
    public static void do_rotate(Deque<Point> points, double theta)
    {
        double alpha = Math.toRadians(theta);
        Numerical_Matrix rotation_matrix = new Numerical_Matrix(3);
        rotation_matrix.set_value(0,0, Math.cos(alpha));
        rotation_matrix.set_value(0, 1, -Math.sin(alpha));
        rotation_matrix.set_value(1, 0, Math.sin(alpha));
        rotation_matrix.set_value(1, 1, Math.cos(alpha));


        Numerical_Matrix point_matrix_pos = new Numerical_Matrix(3);
        Numerical_Matrix point_matrix_neg = new Numerical_Matrix(3);

        int size = points.size();

        Point mid_point = get_mid(points);

        int px = mid_point.getiX();
        int py = mid_point.getiY();

        point_matrix_pos.set_value(0,2, px);
        point_matrix_pos.set_value(1,2, py);
        point_matrix_neg.set_value(0,2, -px);
        point_matrix_neg.set_value(1,2, -py);


        Numerical_Matrix coordinate = new Numerical_Matrix(3,1, 1);

        for(int i = 0; i < size; i++)
        {
            Point temp_point = points.removeFirst();
            setMatrixFromPoint(coordinate, temp_point);

            Numerical_Matrix temp_mat = point_matrix_pos.copyOf();

            temp_mat.multiply(rotation_matrix);
            temp_mat.multiply(point_matrix_neg);
            temp_mat.multiply(coordinate);

            temp_point.setLocation(temp_mat.get_d_matrix()[0][0], temp_mat.get_d_matrix()[1][0]);
            points.addLast(temp_point);
        }
    }
    /* Rotation */

    /* Scaling */
    public static void scale(Deque<Point> points, double Sx, double Sy)
    {
        if(!check(points))
        {
            return;
        }
        do_scale(points, Sx, Sy);
        Drawing.drawMess(points);
    }
    public static void do_scale(Deque<Point> points, double Sx, double Sy)
    {
        Numerical_Matrix scale_matrix = new Numerical_Matrix(3);
        scale_matrix.set_value(0,0, Sx);
        scale_matrix.set_value(1,1, Sy);

        Point mid_point = get_mid(points);

        scale_matrix.set_value(0,2, mid_point.getX() * (1 - Sx) );
        scale_matrix.set_value(1,2, mid_point.getY() * (1 - Sy) );

        Numerical_Matrix coordinate = new Numerical_Matrix(3, 1, 1);

        int size = points.size();


        for(int i = 0; i < size; i++)
        {
            Point temp_point = points.removeFirst();
            setMatrixFromPoint(coordinate, temp_point);
            Numerical_Matrix temp_mat = scale_matrix.copyOf();

            temp_mat.multiply(coordinate);
            temp_point.setLocation(temp_mat.get_d_matrix()[0][0], temp_mat.get_d_matrix()[1][0]);

            points.addLast(temp_point);
        }
    }
    /* Scaling */


    /* Project onto 2D */
    public static void project(ArrayList<Numerical_Matrix> coordinates)
    {
        int size = coordinates.size();
        Numerical_Matrix world_matrix = new Numerical_Matrix(4);
        world_matrix.set_value(2,3, -5);

        Numerical_Matrix projection_matrix = world_matrix;

        for(int i = 0; i < size; i++)
        {
            world_matrix.multiply(coordinates.get(i));
        }
    }

    /* Project onto 2D */


    /* utils */
    public static void setMatrixFromPoint(Numerical_Matrix coordinate, Point temp_point)
    {
        coordinate.set_value(0, 0, temp_point.x);
        coordinate.set_value(1, 0, temp_point.y);
    }
    public static boolean check(Deque<Point> points)
    {
        if(points.size() == 0)
            return false;
        return true;
    }
    public static Point get_mid(Deque<Point> points)
    {
        int size = points.size();
        if(size == 0)
            return null;
        int x_sum = 0;
        int y_sum = 0;

        for(int i = 0; i < size; i++)
        {
            Point temp_point = points.removeFirst();
            x_sum += temp_point.x;
            y_sum += temp_point.y;
            points.addLast(temp_point);
        }

        int px = x_sum / size;
        int py = y_sum / size;

        return new Point(px, py);
    }
    /* utils */

}
