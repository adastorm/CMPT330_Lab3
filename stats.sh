#!/bin/bash

#JOnah Watts
#CMPT 330
#200206

#!/usr/bin/bash

# Jonah Watts
# 200206
# CMPT330

# First see if the command is valid
if $1 ; then
    echo "USAGE $0 "
fi

echo "">results

# Loop through every argument
for i in {1..50}; do

    echo -n "$i,">>results.txt
    java Simulator $i 0 500 5 2 | grep "Ready Processes: " | sed 's/Ready Processes: //'>>results.txt

done