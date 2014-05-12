Akka EC2
========

A sample application which demonstrates how to run an auto-scaling cluster
in Amazon EC2, including code for discovery of sibling nodes at startup time.

There is an explanation of the approach taken in [this blog post](http://chrisloy.net/2014/05/11/akka-cluster-ec2-autoscaling.html). 

Build
-----

You'll need SBT installed for this to work. Checkout the code, fire up a
terminal in the project's root directory and build it:

```bash
sbt clean assembly
```

This builds a jar containing all the dependencies needed.

Local usage
-----------

To test locally, try starting a few instances in separate tabs like this:

```bash
java -jar -Dakka.port=2551 target/scala-2.10/akka-ec2.jar
java -jar -Dakka.port=2552 target/scala-2.10/akka-ec2.jar
java -jar -Dakka.port=2553 target/scala-2.10/akka-ec2.jar
java -jar -Dakka.port=2554 target/scala-2.10/akka-ec2.jar
```

Note that the first instance you start locally must be run on port 2551. You
should see some output that the nodes are talking to each other.

Usage on EC2
------------

Ensure in your security group settings that port 2551 is open for both ingress
and egress. On each instance, you want to start up the application like this:

```bash
java -jar akka-ec2.jar
```

The node should look for other instances running in the same autoscaling group, and
use them to join the cluster. If it is the first instance to start up, then it will
form a new cluster.

Note that if you start two or more instances at the same time then you run the risk
of splitting the cluster.
