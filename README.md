# jcache-samples
This contains the samples used for the Java Colombo Meetup on Distributed Caching with Java. 
This demonstrates the use of Javax JCache with Hazelcast as the caching server. Hazelcast is running
in client-server mode where the Hazelcast cluster instances are running separately from the Application.
It is also possible to run Hazelcast in embedded mode where the application and the Hazelcast instance 
reside within the same JVM.

This has three maven modules. Each module can be run by executing the following command.

`mvn test -Prun`

1. cache-server-starter

Starts a hazelcast instance which will act as a distributed caching server. Run `mvn test -Prun` two-three times 
to get a Hazelcast cluster with two to three nodes.

2. cache-setter

This creates a cache, and adds a set of objects to it.

3. cache-getter

This reads the set of objects to it that was added by cache-setter module.

You can analyze the cache usage across the distributed cluster nodes via the Hazelcast Mancenter.
To run mancenter,

1. Download Hazelcast, and Unpack the distribution.
2. Go to hazelcast-x.x/mancenter/
3. Execute the command.

In Linux - ./startManCenter.sh 8080 mancenter
In Windows - ./startManCenter.bat 8080 mancenter (not tested)

4. Spin up a hazelcast cluster, if not done so already.
5. Point your browser to http://localhost:8080/mancenter.
