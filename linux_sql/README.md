#Introduction
The purpose of this project is to help teams monitor the resource usage (memory, CPU, etc…) of different hosts on a network. The idea is that the hardware specifications of each host is recorded and stored in a table in a database and then every one minute each host reports their resource usage which is then also stored in a separate table. The collected data can then be used and analyzed by the team to make decisions regarding the network. I used postgres for the RDBMS functionality and wrote two bash scripts to collect and insert hardware information and usage data to the database. I used git extensively for version control purposes.

# Quick Start

The following series of commands should be used to set up and start a psql docker container, create the necessary tables, insert the hardware specs of the system into the database, add usage data to the database, and set up the scripts to run automatically.

To create a psql docker container
```
./scripts/psql_docker.sh create db_username db_password
```
To start the container
```
./scripts/psql_docker.sh start
```
Create tables using ddl.sql
```
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```
Insert hardware specs data into the DB using host_info.sh
```
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```
Insert hardware usage data into the DB using host_usage.sh
```
scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
```
Crontab setup
```
crontab -e

bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log

crontab -l
```
