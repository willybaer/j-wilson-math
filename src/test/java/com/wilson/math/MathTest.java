package com.wilson.math;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by wd on 01.12.17.
 */
public class MathTest {

    @Test
    public void should_return_correct_sub_matrix() {
        float[][] results = new Matrix.Math().calculateSubMatrix(new float[][]{
                {1, 1, -1, 3},
                {1, 2, -2, 2},
                {2, -1, 2, 15}
        });

        assertThat(results.length).isEqualTo(2);
        assertThat(results[0].length).isEqualTo(3);
        assertThat(results).isEqualTo(new float[][]{
                {-1, 1, 1},
                {3, -4, -9}
        });
    }

    @Test
    public void should_return_correct_result_for_1x2_linear_equation() {
        Matrix matrix = Matrix.matrix(1, 2)
                .add(-4, -8);

        float[] result = matrix.math().linearEquation();

        assertThat(result).isNotNull();
        assertThat(result.length).isEqualTo(1);
        assertThat(result[0]).isEqualTo(2.f);
    }

    @Test
    public void should_return_correct_result_for_3x4_linear_equation() {
        Matrix matrix = Matrix.matrix(3, 4)
                .add(1, 1, -1, 3)
                .add(1, 2, -2, 2)
                .add(2, -1, 2, 15);

        float[] result = matrix.math().linearEquation();

        assertThat(result).isNotNull();
        assertThat(result.length).isEqualTo(3);
        assertThat(result[0]).isEqualTo(4.f);
        assertThat(result[1]).isEqualTo(5.f);
        assertThat(result[2]).isEqualTo(6.f);
    }

}