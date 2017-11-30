package com.wilson.math;

/**
 * Created by wd on 30.11.17.
 */
public class Matrix {

    private float[][] matrix;
    private int lastAddedRow = 0;

    private Matrix(int rows, int columns) {
        matrix = new float[rows][columns];
    }

    public static Matrix matrix(int rows, int columns) {
        if (columns - rows != 1) {
            throw new MatrixException("The number of columns must the sum of rows + 1");
        }
        return new Matrix(rows, columns);
    }

    public Matrix add(float... row) {
        matrix[lastAddedRow] = row.clone();
        lastAddedRow++;
        return this;
    }

    public float[][] primitive() {
        return matrix;
    }

    public int rows() {
        return matrix.length;
    }

    public Math math() {
        return new Math(primitive());
    }

    /**
     * Math class for mathimatical calculations for the matrix class
     */
    public static class Math {

        private float[][] matrix;

        public Math(float[][] matrix) {
            this.matrix = matrix;
        }

        public Math() {

        }

        public float[] linearEquation() {
            float[][] linearEqMatrix = matrix.clone();
            float[] result = recursiveLinearEquation(linearEqMatrix, 0);
            return result;
        }

        private float[] recursiveLinearEquation(float[][] linearEqMatrix, int resultIndex) {
            float[] results;
            if (linearEqMatrix.length == 1 && linearEqMatrix[0].length == 2) {

                results = new float[resultIndex + 1];
                results[resultIndex] = linearEqMatrix[0][1] / linearEqMatrix[0][0];
            } else {

                float firstElement = linearEqMatrix[0][0];
                float lastElement = linearEqMatrix[0][linearEqMatrix[0].length - 1];

                results = recursiveLinearEquation(calculateSubMatrix(linearEqMatrix), resultIndex + 1);
                for (int i = results.length - 1; i > 0; i--) {
                    lastElement -= (linearEqMatrix[0][i] * results[i]);
                }

                results[resultIndex] = lastElement / firstElement;
            }
            return results;
        }

        public float[][] calculateSubMatrix(float[][] linearEqMatrix) {
            float[][] subEqMatrix = new float[linearEqMatrix.length - 1][linearEqMatrix[0].length - 1];

            for (int i = 1; i < linearEqMatrix.length; i++) {
                float[] firstRow = linearEqMatrix[0].clone();
                float firstRowAcc = firstRow[0];
                float[] currentRow = linearEqMatrix[i].clone();
                float currentRowAcc = linearEqMatrix[i][0];

                for(int j = 1; j < firstRow.length; j++) {
                    firstRow[j] *= currentRowAcc;
                    currentRow[j] *= firstRowAcc;
                    subEqMatrix[i - 1][j - 1] = firstRow[j] - currentRow[j];
                }
            }

            return subEqMatrix;
        }
    }
}
