package Util;

import Jama.Matrix;

import static Util.MatrixUtil.*;

public class RegressionUtil {
    public static double[] estimateParameters(double[][] xData, double[][] yData, int indVarCount) {
        double resX[][] = new double[indVarCount + 1][indVarCount + 1];
        double resY[][] = new double[indVarCount + 1][1];
        formRegressionMatrix(xData, yData, resX, resY);
        return solveLinearEquation(resX, resY);
    }

    private static void formRegressionMatrix(double[][] xData,
                                             double[][] yData,
                                             double[][] resX,
                                             double[][] resY) {
        int n = xData.length;
        int k = xData[0].length;
        //forming matrix 1
        double mat1[][] = new double[n + 1][k];
        for (int j = 0; j < k; j++)
            mat1[0][j] = 1;
        for (int i = 1; i < (n + 1); i++) {
            for (int j = 0; j < k; j++) {
                mat1[i][j] = xData[i - 1][j];
            }
        }

        //forming matrix2

        matrixMultiply(mat1, transpose(mat1), resX);
        matrixMultiply(mat1, yData, resY);
        scalarMultiply(resX, 1.0d / ((double) k));
        scalarMultiply(resY, 1.0d / ((double) k));
    }

    //    public static double[] solveLinearEquation(double[][] mat, double[][] constants) {
//
//        int n = mat.length;
//        //inverse of matrix mat[][]
//        double inverted_mat[][] = invert(mat);
//
//        //Multiplication of mat inverse and constants
//        double result[] = new double[n];
//        for (int i = 0; i < n; i++) {
//            for (int k = 0; k < n; k++) {
//                result[i] = result[i] + inverted_mat[i][k] * constants[k][0];
//            }
//        }
//        return result;
//    }
    public static double[] solveLinearEquation(double[][] lhsArr, double[][] rhsArr) {

        int n = rhsArr.length;
        Matrix lhs = new Matrix(lhsArr);
        Matrix rhs = new Matrix(rhsArr);
        Matrix ans = lhs.solve(rhs);

        double result[] = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = ans.get(i, 0);
        }
        return result;
    }
}