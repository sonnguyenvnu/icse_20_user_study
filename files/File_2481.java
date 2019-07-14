/**
 * Copyright (C) 2013-2017 Vasilis Vryniotis <bbriniotis@datumbox.com>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hankcs.hanlp.classification.statistics;

/**
 * �??供常�?连续分布的概率密度函数和累积分布函数计算<br>
 *
 * @author Vasilis Vryniotis <bbriniotis@datumbox.com>
 */
public class ContinuousDistributions
{

    /**
     * 给定�?�方值和自由度，计算从0到x的累积分布函数值<br>
     *
     * @param x  �?�方值
     * @param df 自由度
     * @return 从0到x的累积分布函数值
     * @throws IllegalArgumentException
     */
    public static double ChisquareCdf(double x, int df) throws IllegalArgumentException
    {
        if (df <= 0)
        {
            throw new IllegalArgumentException();
        }

        return GammaCdf(x / 2.0, df / 2.0);
    }

    /**
     * 给定高斯函数的z值，返回p值（累积分布函数值）<br>
     * http://jamesmccaffrey.wordpress.com/2010/11/05/programmatically-computing-the-area-under-the-normal-curve/
     *
     * @param z 从负无穷到正无穷的值
     * @return 高斯函数累积分布函数值
     */
    public static double GaussCdf(double z)
    {
        // input = z-value (-inf to +inf)
        // output = p under Normal curve from -inf to z
        // e.g., if z = 0.0, function returns 0.5000
        // ACM Algorithm #209
        double y; // 209 scratch variable
        double p; // result. called ‘z’ in 209
        double w; // 209 scratch variable

        if (z == 0.0)
        {
            p = 0.0;
        }
        else
        {
            y = Math.abs(z) / 2.0;
            if (y >= 3.0)
            {
                p = 1.0;
            }
            else if (y < 1.0)
            {
                w = y * y;
                p = ((((((((0.000124818987 * w
                    - 0.001075204047) * w + 0.005198775019) * w
                    - 0.019198292004) * w + 0.059054035642) * w
                    - 0.151968751364) * w + 0.319152932694) * w
                    - 0.531923007300) * w + 0.797884560593) * y * 2.0;
            }
            else
            {
                y = y - 2.0;
                p = (((((((((((((-0.000045255659 * y
                    + 0.000152529290) * y - 0.000019538132) * y
                    - 0.000676904986) * y + 0.001390604284) * y
                    - 0.000794620820) * y - 0.002034254874) * y
                    + 0.006549791214) * y - 0.010557625006) * y
                    + 0.011630447319) * y - 0.009279453341) * y
                    + 0.005353579108) * y - 0.002141268741) * y
                    + 0.000535310849) * y + 0.999936657524;
            }
        }

        if (z > 0.0)
        {
            return (p + 1.0) / 2.0;
        }

        return (1.0 - p) / 2.0;
    }

    /**
     * Log Gamma Function
     *
     * @param Z
     * @return
     */
    public static double LogGamma(double Z)
    {
        double S = 1.0 + 76.18009173 / Z - 86.50532033 / (Z + 1.0) + 24.01409822 / (Z + 2.0) - 1.231739516 / (Z + 3.0) + 0.00120858003 / (Z + 4.0) - 0.00000536382 / (Z + 5.0);
        double LG = (Z - 0.5) * Math.log(Z + 4.5) - (Z + 4.5) + Math.log(S * 2.50662827465);

        return LG;
    }

    /**
     * Internal function used by GammaCdf
     *
     * @param x
     * @param A
     * @return
     */
    protected static double Gcf(double x, double A)
    {
        // Good for X>A+1
        double A0 = 0;
        double B0 = 1;
        double A1 = 1;
        double B1 = x;
        double AOLD = 0;
        double N = 0;
        while (Math.abs((A1 - AOLD) / A1) > .00001)
        {
            AOLD = A1;
            N = N + 1;
            A0 = A1 + (N - A) * A0;
            B0 = B1 + (N - A) * B0;
            A1 = x * A0 + N * A1;
            B1 = x * B0 + N * B1;
            A0 = A0 / B1;
            B0 = B0 / B1;
            A1 = A1 / B1;
            B1 = 1;
        }
        double Prob = Math.exp(A * Math.log(x) - x - LogGamma(A)) * A1;

        return 1.0 - Prob;
    }

    /**
     * Internal function used by GammaCdf
     *
     * @param x
     * @param A
     * @return
     */
    protected static double Gser(double x, double A)
    {
        // Good for X<A+1.
        double T9 = 1 / A;
        double G = T9;
        double I = 1;
        while (T9 > G * 0.00001)
        {
            T9 = T9 * x / (A + I);
            G = G + T9;
            ++I;
        }
        G = G * Math.exp(A * Math.log(x) - x - LogGamma(A));

        return G;
    }

    /**
     * 伽马函数
     *
     * @param x
     * @param a
     * @return
     * @throws IllegalArgumentException
     */
    protected static double GammaCdf(double x, double a) throws IllegalArgumentException
    {
        if (x < 0)
        {
            throw new IllegalArgumentException();
        }

        double GI = 0;
        if (a > 200)
        {
            double z = (x - a) / Math.sqrt(a);
            double y = GaussCdf(z);
            double b1 = 2 / Math.sqrt(a);
            double phiz = 0.39894228 * Math.exp(-z * z / 2);
            double w = y - b1 * (z * z - 1) * phiz / 6;  //Edgeworth1
            double b2 = 6 / a;
            int zXor4 = ((int) z) ^ 4;
            double u = 3 * b2 * (z * z - 3) + b1 * b1 * (zXor4 - 10 * z * z + 15);
            GI = w - phiz * z * u / 72;        //Edgeworth2
        }
        else if (x < a + 1)
        {
            GI = Gser(x, a);
        }
        else
        {
            GI = Gcf(x, a);
        }

        return GI;
    }

    /**
     * 给定�?�方分布的p值和自由度，返回�?�方值。内部采用二分�?�索实现，移�?自JS代�?：
     * http://www.fourmilab.ch/rpkp/experiments/analysis/chiCalc.js
     *
     * @param p  p值（置信度）
     * @param df
     * @return
     */
    public static double ChisquareInverseCdf(double p, int df)
    {
        final double CHI_EPSILON = 0.000001;   /* Accuracy of critchi approximation */
        final double CHI_MAX = 99999.0;        /* Maximum chi-square value */
        double minchisq = 0.0;
        double maxchisq = CHI_MAX;
        double chisqval = 0.0;

        if (p <= 0.0)
        {
            return CHI_MAX;
        }
        else if (p >= 1.0)
        {
            return 0.0;
        }

        chisqval = df / Math.sqrt(p);    /* fair first value */
        while ((maxchisq - minchisq) > CHI_EPSILON)
        {
            if (1 - ChisquareCdf(chisqval, df) < p)
            {
                maxchisq = chisqval;
            }
            else
            {
                minchisq = chisqval;
            }
            chisqval = (maxchisq + minchisq) * 0.5;
        }

        return chisqval;
    }
}
