FROM ubuntu

RUN apt-get update
RUN apt-get install default-jre -y

COPY out/production/rmm-calculator /opt/rmm-calculator

ENTRYPOINT java com.corobceanu.RMMCalculator