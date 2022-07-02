from centos:centos7
run yum update -y
run yum install java-1.8.0-openjdk -y
run yum install maven -y
run yum install git -y
workdir /root
run git clone https://github.com/yutadd/SimplestDiscordBot.git
workdir SimplestDiscordBot/
run mvn install
workdir target