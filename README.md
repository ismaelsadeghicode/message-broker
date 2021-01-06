# message-broker

### install zookeeper
````
- https://phoenixnap.com/kb/install-apache-zookeeper
````
### config zookeeper
````
sudo nano /opt/zookeeper/conf/zoo.cfg
````

### start & stop zookeeper
````
sudo  bin/zookeeper-server-start.sh config/zookeeper.properties
sudo  bin/zookeeper-server-stop.sh config/zookeeper.properties
````



sudo bin/kafka-server-start.sh config/server.properties      





## Erorr
### not start kafka
````
1- rm -r /var/lib/kafka/data/*
2- sudo  bin/zookeeper-server-start.sh config/zookeeper.properties
3- sudo bin/kafka-server-start.sh config/server.properties       
````
