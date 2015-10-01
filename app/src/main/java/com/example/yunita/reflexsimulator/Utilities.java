/*
Utilities performs array manipulation.

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

import java.util.Arrays;

public class Utilities {

    public Utilities() {

    }

    public double[] convertToDoubleArray(String strings[]) {
        double newArray[] = new double[strings.length];
        for (int i = 0; i < strings.length; i++) {
            newArray[i] = Double.parseDouble(strings[i]);
        }
        return newArray;
    }

    public double[] getSortedSubarray(int last, double savedData[]) {
        if (savedData.length >= last) {
            int end = savedData.length; //exclusive
            int start = savedData.length - last;
            double subArray[] = Arrays.copyOfRange(savedData, start, end);
            Arrays.sort(subArray);
            return subArray;
        } else {
            Arrays.sort(savedData);
            return savedData;
        }
    }

}
