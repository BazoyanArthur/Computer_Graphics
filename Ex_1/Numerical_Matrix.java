public class Numerical_Matrix
{
    private double[][] d_matrix;    // i tried using generic type, but it took too much time so just went like this, i know its not efficient but it will do.
    private int[][] matrix;
    int rows;
    int columns;

    /* constructors start here */
    // default constructor
    Numerical_Matrix()
    {
        initialize_matrix(0, 0);
    }

    // empty matrix with set size
    Numerical_Matrix(int r, int c)
    {
        initialize_matrix(r, c);
    }

    // identity matrix
    Numerical_Matrix(int square_size)
    {
        initialize_identity(square_size, square_size);
    }

    // empty matrix with set size and set values in each cell
    Numerical_Matrix(int r, int c, int fill)
    {
        initialize_matrix(r, c, fill);
        initialize_matrix(r, c, (double)fill );
    }

    Numerical_Matrix(int r, int c, double fill)
    {
        initialize_matrix(r, c,(int)fill);
        initialize_matrix(r, c, fill);
    }

    //takes the 2d array, the rows and columns to create the matrix in the class
    Numerical_Matrix(int[][] mat, int r, int c)
    {
        set_size(r,c);
        this.matrix = new int[r][c];
        copy_matrix(mat, r, c);
        sync_d_to_i();
    }

    Numerical_Matrix(double[][] mat, int r, int c)
    {
        set_size(r,c);
        this.d_matrix = new double[r][c];
        copy_matrix(mat, r, c);
        sync_i_to_d();
    }

    Numerical_Matrix copyOf()
    {
        return new Numerical_Matrix(this);
    }

    // copy constructor
    private Numerical_Matrix(Numerical_Matrix mat) {
        set_size(mat.get_rows_count(), mat.get_columns_count());
        this.matrix = new int[rows][columns];
        this.d_matrix = new double[rows][columns];
        int[][] temp_mat = mat.get_matrix();
        double[][] temp_dmat = mat.get_d_matrix();
        copy_matrix(temp_mat, mat.get_rows_count(), mat.get_columns_count());
        copy_matrix(temp_dmat, mat.get_rows_count(), mat.get_columns_count());
    }
    /* constructors end here */

    // public
    public void multiply(Numerical_Matrix mat)
    {
        if (columns != mat.get_rows_count()) {
            System.out.println(columns);
            System.out.println(mat.get_rows_count());
            throw new ArithmeticException("Sizes of Matrices don't allow multiplication");
        }

        int[][] temp = new int[rows][mat.get_columns_count()];
        double[][] temp_d = new double [rows][mat.get_columns_count()];

        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < mat.get_columns_count(); ++j)
            {
                int c = 0;
                double d = 0;
                for (int k = 0; k < columns; ++k)
                {
                    c += (matrix[i][k] * mat.get_matrix()[k][j]);
                    d += (d_matrix[i][k] * mat.get_d_matrix()[k][j]);
                }
                temp[i][j] = c;
                temp_d[i][j] = d;
            }
        }
        columns = mat.get_columns_count();
        copy_matrix(temp, rows, columns);
        copy_matrix(temp_d, rows, columns);
    }

    public void transpose()
    {
        int[][] temp_mat = new int[columns][rows];
        double[][] temp_dmat = new double[columns][rows];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                temp_mat[j][i] = matrix[i][j];
                temp_dmat[j][i] = d_matrix[i][j];
            }
        }
        int temp_num = rows;
        rows = columns;
        columns = temp_num;
        copy_matrix(temp_mat, columns, rows);
        copy_matrix(temp_dmat, columns, rows);
    }

    public void print_matrix()
    {
        System.out.println("Integer Matrix");
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Double Matrix");
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                System.out.print(d_matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // get functions
    public int[][] get_matrix()
    {
        return matrix;
    }
    public double[][] get_d_matrix(){
        return d_matrix;
    }

    public int get_rows_count()
    {
        return rows;
    }

    public int get_columns_count()
    {
        return columns;
    }

    // set functions
    private void set_size(int r, int c)
    {
        rows = r;
        columns = c;
    }

    public void set_value(int r, int c, int val)
    {
        matrix[r][c] = val;
        d_matrix[r][c] = val;
    }

    public void set_value(int r, int c, double val)
    {
        matrix[r][c] = (int)val;
        d_matrix[r][c] = val;
    }

    // private functions

    private void copy_matrix(int[][] mat, int r, int c)
    {
        if(rows == r && columns == c) {
            matrix = new int[rows][columns];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    this.matrix[i][j] = mat[i][j];
                }
            }
        }
    }
    private void copy_matrix(double[][] mat, int r, int c)
    {
        if(rows == r && columns == c) {
            d_matrix = new double[rows][columns];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    this.d_matrix[i][j] = mat[i][j];
                }
            }
        }
    }


    private void initialize_matrix(int r, int c )
    {
        initialize_matrix(r, c, 0);
        initialize_matrix(r, c, 0.);
    }
    private void initialize_matrix(int r, int c, int fill)
    {
        set_size(r,c);
        matrix = new int[r][c];
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                this.matrix[i][j] = fill;
            }
        }
    }
    private void initialize_matrix(int r, int c, double fill)
    {
        set_size(r,c);
        d_matrix = new double[r][c];
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                this.d_matrix[i][j] = fill;
            }
        }
    }
    private void initialize_identity(int r, int c)
    {
        if(r != c)
            throw new ArithmeticException("Cant create non square identity");

        set_size(r,c);
        matrix = new int[r][c];
        d_matrix = new double[r][c];
        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                this.matrix[i][j] = 0;
                this.d_matrix[i][j] = 0;
                if (i == j)
                {
                    this.matrix[i][j] = 1;
                    this.d_matrix[i][j] = 1;
                }
            }
        }
    }

    //sync double matrix to integer
    private void sync_d_to_i()
    {
        d_matrix = new double[rows][columns];
        for(int i=0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                d_matrix[i][j] = matrix[i][j];
            }
        }
    }

    // sync integer matrix to double matrix
    private void sync_i_to_d()
    {
        matrix = new int[rows][columns];
        for(int i=0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                matrix[i][j] = (int)d_matrix[i][j];
            }
        }
    }
}