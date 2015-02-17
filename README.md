# Jackson - ["Could not resolve type id" issue ][jackson-databind-issue] #

Reproducible failing unit test for a Jackson issue when `ObjectMapper.registerSubtypes` is called after the first read/write use of the mapper: [jackson-databind issue #708][jackson-databind-issue]

## Howto ##

Prerequisites:
  - Java 6, 7 or 8

On Linux:

```
# ./gradlew --info build
```

On Windows:

```
# gradlew --info build
```

### The stacktrace ###

On Travis: [![Build Status](https://travis-ci.org/bwaldvogel/jackson-issue.png?branch=master)](https://travis-ci.org/bwaldvogel/jackson-issue)

The `JacksonTest.failingTest` fails:
```
com.fasterxml.jackson.databind.JsonMappingException: Could not resolve type id 'OtherValue' into a subtype of [simple type, class Value]: known type ids = [FirstValue, Value]
 at [Source: {"value":{"@type":"OtherValue","string":"some other value"}}; line: 1, column: 11] (through reference chain: Holder["value"])
	at com.fasterxml.jackson.databind.JsonMappingException.from(JsonMappingException.java:148)
	at com.fasterxml.jackson.databind.DeserializationContext.unknownTypeException(DeserializationContext.java:948)
	at com.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase._handleUnknownTypeId(TypeDeserializerBase.java:275)
	at com.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase._findDeserializer(TypeDeserializerBase.java:162)
	at com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer._deserializeTypedForId(AsPropertyTypeDeserializer.java:110)
	at com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer.deserializeTypedFromObject(AsPropertyTypeDeserializer.java:95)
	at com.fasterxml.jackson.databind.deser.AbstractDeserializer.deserializeWithType(AbstractDeserializer.java:131)
	at com.fasterxml.jackson.databind.deser.SettableBeanProperty.deserialize(SettableBeanProperty.java:521)
	at com.fasterxml.jackson.databind.deser.impl.FieldProperty.deserializeAndSet(FieldProperty.java:101)
	at com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap.findDeserializeAndSet(BeanPropertyMap.java:285)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.vanillaDeserialize(BeanDeserializer.java:248)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:136)
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:3562)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:2578)
	at JacksonTest.writeReadValue(JacksonTest.java:38)
	at JacksonTest.failingTest(JacksonTest.java:33)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:483)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)

```

[jackson-databind-issue]: https://github.com/FasterXML/jackson-databind/issues/708
