export PATH=$HOME/programs/rmq/$RMQ_VERSION/sbin:$PATH

rabbitmqadmin -P $RMQ_MANAGEMENT_PORT declare exchange name="$RMQ_EXCHANGE_DIRECT" type="direct" durable=true > /dev/null 2>&1

rabbitmqadmin -P $RMQ_MANAGEMENT_PORT declare queue name="$RMQ_QUEUE_CONSUMED" durable=true > /dev/null 2>&1
rabbitmqadmin -P $RMQ_MANAGEMENT_PORT declare binding source="$RMQ_EXCHANGE_DIRECT" destination="$RMQ_QUEUE_CONSUMED" routing_key="$RMQ_KEY_CONSUMED" > /dev/null 2>&1

rabbitmqadmin -P $RMQ_MANAGEMENT_PORT declare queue name="$RMQ_QUEUE_UNCONSUMED" durable=true > /dev/null 2>&1
rabbitmqadmin -P $RMQ_MANAGEMENT_PORT declare binding source="$RMQ_EXCHANGE_DIRECT" destination="$RMQ_QUEUE_UNCONSUMED" routing_key="$RMQ_KEY_UNCONSUMED" > /dev/null 2>&1