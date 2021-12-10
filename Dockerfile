FROM ubuntu
 
RUN apt-get update

RUN apt-get install  -y language-pack-ko
 
# set locale ko_KR
RUN locale-gen ko_KR.UTF-8

ENV LANG ko_KR.UTF-8
ENV LANGUAGE ko_KR.UTF-8
ENV LC_ALL ko_KR.UTF-8

CMD /bin/bash

