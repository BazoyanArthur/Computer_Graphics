import java.awt.event.*;
import java.util.Deque;
import java.util.LinkedList;


public class Main {
    public static void main(String... args) {

        Drawing d = new Drawing();
        Deque<Point> points = new LinkedList<>();
        Deque<Point> object_points = new LinkedList<>();


        Drawing.frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    points.addFirst(new Point(e.getX(),e.getY()));

                    if(points.size() % 2 == 0)
                    {
                        Point point1 = points.remove();
                        Point point2 = points.remove();
                        points.addFirst(point2);
                        points.addFirst(point1);
                        Drawing.drawLine(point1, point2);
                    }
                }
            }
        });

        Drawing.frame.addKeyListener( new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int tx = 0, ty = 0;
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    ty = -1;
                    Action.move(points,tx,ty);
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    ty = 1;
                    Action.move(points,tx,ty);
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    tx = -1;
                    Action.move(points,tx,ty);
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    tx = 1;
                    Action.move(points,tx,ty);
                }
                else if(e.getKeyCode() == KeyEvent.VK_R)
                {
                    // rotating clock wise
                    double alpha = 10;
                    Action.rotate(points, alpha);
                }
                else if(e.getKeyCode() == KeyEvent.VK_T)
                {
                    // rotating counter clock wise
                    double alpha = -10;
                    Action.rotate(points, alpha);
                }
                else if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    // Scale Up
                    double Sx = 1.25;
                    double Sy = 1.25;
                    Action.scale(points, Sx, Sy);
                }
                else if(e.getKeyCode() == KeyEvent.VK_D)
                {
                    // Scale Down
                    double Sx = 0.75;
                    double Sy = 0.75;
                        Action.scale(points, Sx, Sy);
                }
                else if(e.getKeyCode() == KeyEvent.VK_K)
                {
                    Cube cube = new Cube(2, new Point(250,250, 250) );
                    cube.drawCube(d);
                }

            }
        });

        Drawing.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Drawing.frame.dispose();
                Drawing.graphics.dispose();
                System.exit(0);
            }
        });
    }
}