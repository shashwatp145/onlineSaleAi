#!/bin/bash
N=$1
if [ $N -lt 10 ]                                    //If n is less than 10
then
        OUT=$((N*N))
elif [ $N -gt 10 ] && [ $N -lt 20 ]  //BUG 1       (If n is between 10 and 20(exclusive))
then
        OUT=1
        LIM=$((N - 10))
        for (( i=1; i<=$LIM; i++ ))  //BUG 2
        do
                OUT=$((OUT * i))
        done
else                                                //If n is greater than 20

        LIM=$((N - 20))
        OUT=$((LIM * LIM))
        OUT=$((OUT + LIM))           //BUG 3
        OUT=$((OUT / 2))
fi
echo $OUT
