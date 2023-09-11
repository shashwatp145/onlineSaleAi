#!/bin/bash
N=$1
if [ $N -lt 10 ]
then
        OUT=$((N*N))
elif [ $N -ge 10 ] && [ $N -le 20 ]
then
        OUT=1
        LIM=$((N - 10))
        for (( i=1; i<$LIM; i++ ))
        do
                OUT=$((OUT * i))
        done
else
        LIM=$((N - 20))
        OUT=$((LIM * LIM))
        OUT=$((OUT + LIM))
        OUT=$((OUT / 2))
fi
echo $OUT