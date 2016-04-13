




echo $PATH
DB_PATH=/tmp/applifire/db/LFJ9EACI2XJYEINHXXUCKG/F3E3549F-1F21-4239-AD42-38772E25EF0A
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'