The PRODUCER-CONSUMER program creates a user defined number of producer and consumer threads which
continually add new integer objects to a buffer without conflict between the threads. An addition
or removal will be blocked if the buffer is full or empty respectively.

USAGE: From the command line, compile the program with "javac ProducerConsumer.java". After
compilation, run the program with
"java [main thread sleep time in ms] [num producer threads] [num consumer threads]"