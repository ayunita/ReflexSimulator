/*
Statistic performs calculation to measure the maximum, minimum, mean, and
median user's reflex.

Copyright (C) 2015  Andriani Yunita

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.yunita.reflexsimulator;

public class Statistic {

    public Statistic() {

    }

    public double max(double rangedSortedArray[]) {
        return rangedSortedArray[rangedSortedArray.length - 1];
    }

    public double min(double rangedSortedArray[]) {
        return rangedSortedArray[0];
    }

    public double median(double rangedSortedArray[]) {
        double median = 0;
        int size = rangedSortedArray.length;
        int mid = size / 2;
        if (size % 2 == 0) {
            double left = rangedSortedArray[mid - 1];
            double right = rangedSortedArray[mid];
            median = (left + right) / 2;
        } else {
            median = rangedSortedArray[mid];
        }
        return median;
    }

    public double average(double rangedSortedArray[]) {
        double mean = 0;
        for (double i : rangedSortedArray) {
            mean += i;
        }
        return (mean / rangedSortedArray.length);
    }

}
