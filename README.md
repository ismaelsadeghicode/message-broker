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
cd /opt/kafka/

sudo  bin/zookeeper-server-start.sh config/zookeeper.properties
sudo  bin/zookeeper-server-stop.sh config/zookeeper.properties
````
### start kafka
````
sudo bin/kafka-server-start.sh config/server.properties      
````

### create topics in kafka
````
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 6 --topic kafka_example
````

### kafka console consumer
````
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic kafka_example --from-beginning
````

**************************
### producer
bin/kafka-console-producer.sh --broker-list  localhost:9092 --topic kafka_example



*************************

## Erorr
### not start kafka
````
1- rm -r /var/lib/kafka/data/*
2- sudo  bin/zookeeper-server-start.sh config/zookeeper.properties
3- sudo bin/kafka-server-start.sh config/server.properties       
````
