#! /bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)

cpu_number=$(lscpu | grep "CPU(s):" | awk '{print $2}' | awk '!/node0/')
cpu_architecture=$(lscpu | grep "Architecture" | awk '{print $2}')
cpu_model=$(lscpu | grep "Model name" | awk -F': ' '{print $2}' | awk '{$1=$1;print}')
cpu_mhz=$(lscpu | grep "CPU MHz" | awk '{print $3}')
l2_cache=$(lscpu | grep "L2 cache" | awk '{print $3}' | sed 's/[^0-9]//g')
total_mem=$(vmstat --unit M | tail -1 | awk '{print $4}')
timestamp=$(vmstat -t | awk '{print $18, $19}' | tail -n1)
# I have taken out the id column from the insert command. Not sure if this will work.
# Seemes to be having a problem generating new ids since 1 is already in use. idk why
# it isn't just moving on to 2 or 3 etc...?
insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, timestamp, total_mem)    VALUES('$hostname','$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$timestamp', '$total_mem')";

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?