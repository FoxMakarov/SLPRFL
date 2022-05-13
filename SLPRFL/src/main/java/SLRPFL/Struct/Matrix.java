package SLRPFL.Struct;

import org.omg.CosNaming._BindingIteratorImplBase;

import java.text.DecimalFormat;

import java.util.*;

public class Matrix{

    private int row;//行

    private int col;//列

    //private double value;

    Double [][]Data;



    public Matrix(int row, int col) {

        this.row = row;

        this.col = col;

        Data = new Double[row][col];

        for(int i = 0; i<row;i++)
            for(int j = 0; j<col;j++) {
                Data[i][j] = 0.0;
            }

    }

    public Matrix(int row, int col, Double[][]data) {

        this.row = row;

        this.col = col;

        Data = data;

    }

    public void setMatrix(int row , int col, Double value) {

        this.Data[row][col] = value;

    }

    public Double getValue(int row, int col) {

        return Data[row][col] ;

    }

    public int width() {

        return row;

    }

    public int height() {

        return col;

    }

    public Matrix add(Matrix b) {

        if(this.width() != b.width() && this.height() != b.height()) {

            return null;

        }

        Double add[][] = new Double[this.row][this.col];

        for(int i = 0;i<row;i++) {

            for(int j = 0;j<col;j++) {

                add[i][j] = this.Data[i][j] + b.Data[i][j];

            }

        }

        Matrix another = new Matrix(this.row, this.col, add);

        return another;

    }

    public Matrix multiply(Matrix b) {

        if(this.col != b.row) {

            return null;

        }

        Double mul[][] = new Double[this.row][b.col];

        Double temp = 0.0;

        for(int i = 0;i<this.row;i++) {

            for(int k = 0;k<b.col;k++) {

                for(int j = 0;j<this.col;j++)

                {

                    temp += this.Data[i][j] * b.Data[j][k];

                }

                mul[i][k] = temp;

                temp = 0.0;

            }

        }

        Matrix another = new Matrix(this.row, b.col, mul);

        return another;
    }

    public Matrix multiply(Double d) {
        Double mul[][] = new Double[this.row][this.col];

        for(int i = 0; i<this.row; i++)
            for(int j = 0; j<this.col;j++) {
                mul[i][j] = Data[i][j] * d;
            }

        Matrix another = new Matrix(this.row, this.col, mul);

        return another;
    }

    public void transpose() {

        Double tran[][] = new Double[this.col][this.row];

        for(int i = 0;i<this.row;i++) {

            for(int j = 0;j<this.col;j++) {

                tran[j][i] = this.Data[i][j];

            }

        }

        Data = tran;
        int temp = row;
        row = col;
        col = temp;
    }

    public void transpose(int line){
        if(line > row || line > col) {
            System.out.print("error transpose!\n");
        }

        Double tran[][] = new Double[line][line];

        for(int i = 0;i<line;i++) {

            for(int j = 0;j<line;j++) {

                tran[j][i] = this.Data[i][j];
            }
        }

        for(int i = 0;i<line;i++) {

            for(int j = 0;j<line;j++) {

                this.Data[i][j] = tran[i][j];
            }
        }

    }

    public void normalization() {
        Double[] rowValue = new Double[row];
        for(int i = 0; i<row;i++) {
            rowValue[i] = 0.0;
        }

        for(int i = 0; i < row; i++)
            for(int j = 0; j<col;j++) {
                rowValue[i] = rowValue[i] + Data[i][j];
            }

        for(int i = 0; i < row; i++) {
            if(rowValue[i] >= 0.9)
            for (int j = 0; j < col; j++) {
                Data[i][j] = Data[i][j] / rowValue[i];
            }
        }
    }

    public void normalization(int sz) {
        if(sz > row || sz > col) {
            System.out.print("normalization failed\n");
            return;
        }

        Double[] rowValue = new Double[sz];
        for(int i = 0; i<sz;i++) {
            rowValue[i] = 0.0;
        }

        for(int i = 0; i < sz; i++)
            for(int j = 0; j<sz;j++) {
                rowValue[i] = rowValue[i] + Data[i][j];
            }

        for(int i = 0; i < sz; i++) {
            if(rowValue[i] >= 0.9)
                for (int j = 0; j < sz; j++) {
                    Data[i][j] = Data[i][j] / rowValue[i];
                }
        }
    }

    public void partMul(Double num, int row, int col) {
        if(row > this.row || col > this.col) {
            System.out.print("error partmul\n");
        } else {
            for(int i = 0; i<row;i++)
                for(int j = 0;j<col;j++){
                    Data[i][j] = Data[i][j] * num;
                }
        }
    }

}