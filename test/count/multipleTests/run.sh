for i in 1 2 3 4 5
do
   echo "No. $i test:\n"
   sh countByteByByte-Siebel-buggyLog.sh $i
done
