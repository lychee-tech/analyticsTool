package com.litchi.analytics.tool.utl;


import java.util.ArrayList;
import java.util.List;

public class ListUtl {
    /**
     * split a list into two parts
     *   mid is an index
     *   0 --- mid belongs to part1
     *   others will belong to part2
     *
     *    splitIndex's value can be -1 ~ ins.size()-1
     *
     * @param ins
     * @param mid
     * @param part1
     * @param part2
     */
    public static <T> void splitListByMidIndex(List<T> ins, int mid, List<T> part1, List<T> part2) {
        int size = ins.size();
        if (mid < -1 || mid > size-1) {
            throw new IllegalArgumentException("splitIndex can only take value in the range [-1, list.size()-1] ");
        }

        part1.addAll( ins.subList(0, mid+1) );
        part2.addAll(mid+1 == size? new ArrayList<T>(): ins.subList(mid+1, size));
    }
}
