## kafka dirtributed streaming platform

## ZooKeeper
### start
````
java --version
````

### Creating a User for ZooKeeper
````
useradd zookeeper -m
usermod --shell /bin/bash zookeeper
passwd zookeeper
usermod -aG sudo zookeeper
sudo getent group sudo
````

### Creating a ZooKeeper Data Directory
````
sudo mkdir -p /data/zookeeper
````

### Downloading and Installing ZooKeeper
`````
chown -R zookeeper:zookeeper /data/zookeeper
cd /opt
sudo wget https://downloads.apache.org/zookeeper/zookeeper-3.6.1/apache-zookeeper-3.6.1-bin.tar.gz
sudo tar -xvf apache-zookeeper-3.6.1-bin.tar.gz
mv apache-zookeeper-3.6.1-bin zookeeper
chown -R zookeeper:zookeeper /opt/zookeeper
`````

### Configuring ZooKeeper in Standalone Mode
````
sudo nano /opt/zookeeper/conf/zoo.cfg
````
````
tickTime = 2000

dataDir = /data/zookeeper

clientPort = 2181

initLimit = 5

syncLimit = 2
````

### Starting the ZooKeeper Service
````
cd /opt/zookeeper
sudo bin/zkServer.sh start
bin/zkCli.sh -server 127.0.0.1:2181
help
quit
bin/zkServer.sh stop
sudo nano /etc/systemd/system/zookeeper.service
````
````
[Unit]
Description=Zookeeper Daemon
Documentation=http://zookeeper.apache.org
Requires=network.target
After=network.target

[Service]    
Type=forking
WorkingDirectory=/opt/zookeeper
User=zookeeper
Group=zookeeper
ExecStart=/opt/zookeeper/bin/zkServer.sh start /opt/zookeeper/conf/zoo.cfg
ExecStop=/opt/zookeeper/bin/zkServer.sh stop /opt/zookeeper/conf/zoo.cfg
ExecReload=/opt/zookeeper/bin/zkServer.sh restart /opt/zookeeper/conf/zoo.cfg
TimeoutSec=30
Restart=on-failure

[Install]
WantedBy=default.target
````

````
systemctl daemon-reload
systemctl start zookeeper
systemctl enable zookeeper
systemctl status zookeeper
````

### Configuring Replicated ZooKeeper
````
sudo nano /opt/zookeeper/conf/zoo.cfg
````

````
tickTime=2000
dataDir=/var/zookeeper
clientPort=2181
initLimit=5
syncLimit=2
server.1=[server_ip]:2888:3888
server.2=[server_ip]:2888:3888
server.3=[server_ip]:2888:3888
````

#### Linux start
````
/opt/zookeeper
sudo bin/zkServer.sh start

````


***********************************


### kafka 
````
https://hevodata.com/blog/how-to-install-kafka-on-ubuntu/
https://phoenixnap.com/kb/install-apache-zookeeper/
````

### Download
````
https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.6.2/apache-zookeeper-3.6.2-bin.tar.gz

````

### Install Zookeeper
````
 sudo apt-get install zookeeperd // refrence other repository
 telnet localhost 2181
 at Telnet prompt, we will enter: ruok
 (are you okay) if it’s all okay it will end telnet session and reply with: imok
````

### Create a service User for Kafka
````
sudo adduser --system --no-create-home --disabled-password --disabled-login kafka

cd ~
wget "http://www-eu.apache.org/dist/kafka/1.0.1/kafka_2.12-1.0.1.tgz"

curl http://kafka.apache.org/KEYS | gpg --import
wget https://dist.apache.org/repos/dist/release/kafka/1.0.1/kafka_2.12-1.0.1.tgz.asc
gpg --verify kafka_2.12-1.0.1.tgz.asc kafka_2.12-1.0.1.tgz

sudo mkdir /opt/kafka
sudo tar -xvzf kafka_2.12-1.0.1.tgz --directory /opt/kafka --strip-components 1

rm -rf kafka_2.12-1.0.1.tgz kafka_2.12-1.0.1.tgz.asc

sudo mkdir /var/lib/kafka
sudo mkdir /var/lib/kafka/data
````

##### sudo nano /opt/kafka/config/server.properties
````
delete.topic.enable = true
log.dirs=/var/lib/kafka/data
log.retention.hours=168  #other accepted keys are(log.retention.ms, log.retention.minutes) 
log.retention.bytes=104857600
````

### Ensure Permission of Directories
````
sudo chown -R kafka:nogroup /opt/kafka
sudo chown -R kafka:nogroup /var/lib/kafka
````

### Testing installation
````
sudo /opt/kafka/bin/kafka-server-start.sh /opt/kafka/config/server.properties
/opt/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
/opt/kafka/bin/kafka-topics.sh --list --zookeeper localhost:2181
/opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
/opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning

    Hello world!!!

````

### Launching Kafka as a Service on Startup
````
sudo nano /etc/systemd/system/kafka.service
````
````
[Unit]
Description=High-available, distributed message broker
After=network.target
[Service]
User=kafka
ExecStart=/opt/kafka/bin/kafka-server-start.sh /opt/kafka/config/server.properties
[Install]
WantedBy=multi-user.target
````

````
ExecStart=/opt/kafka/bin/kafka-server-start.sh /opt/kafka/config/server.properties > /opt/kafka/server.log

sudo systemctl start kafka.service

sudo systemctl enable kafka.service

sudo systemctl status kafka.service

````

### Setting up Multi-node Cluster
````
node-1 with ip 10.0.0.1
node-2 with ip 10.0.0.2
node-3 with ip 10.0.0.3


/opt/kafka/config/server.properties

for node-2 broker.id=1
for node-3 broker.id=2

zookeeper.connect=10.0.0.1:2181,10.0.0.2:2181,10.0.0.3:2181

/etc/zookeeper/conf/zoo.cfg

server.0=10.0.0.1:2888:3888
server.1=10.0.0.2:2888:3888
server.2=10.0.0.3:2888:3888

sudo systemctl restart zookeeper.service
````




### start kafka

#### create topic
````
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 6 --topic kafka_example

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic kafka_example --from-beginning
````

### docker
````
sudo apt-get install docker-compose

docker-compose up
````