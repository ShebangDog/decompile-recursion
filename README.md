## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

### Output
```
root@bd84539d1e5d:/decompile-recursion# javap -c -p ./target/scala*/classes/Main$.class
Compiled from "Main.scala"
public final class Main$ implements java.io.Serializable {
  public static final Main$ MODULE$;

  private Main$();
    Code:
       0: aload_0
       1: invokespecial #18                 // Method java/lang/Object."<init>":()V
       4: return

  public static {};
    Code:
       0: new           #2                  // class Main$
       3: dup
       4: invokespecial #21                 // Method "<init>":()V
       7: putstatic     #23                 // Field MODULE$:LMain$;
      10: return

  private java.lang.Object writeReplace();
    Code:
       0: new           #27                 // class scala/runtime/ModuleSerializationProxy
       3: dup
       4: ldc           #2                  // class Main$
       6: invokespecial #30                 // Method scala/runtime/ModuleSerializationProxy."<init>":(Ljava/lang/Class;)V
       9: areturn

  public void main(java.lang.String[]);
    Code:
       0: aload_0
       1: bipush        10
       3: invokedynamic #53,  0             // InvokeDynamic #0:apply:()Lscala/Function0;
       8: invokevirtual #57                 // Method loop:(ILscala/Function0;)V
      11: aload_0
      12: bipush        10
      14: invokedynamic #62,  0             // InvokeDynamic #1:apply:()Lscala/Function0;
      19: invokevirtual #65                 // Method recurse:(ILscala/Function0;)V
      22: return

  public <A> void loop(int, scala.Function0<A>);
    Code:
       0: iload_1
       1: istore_3
       2: iconst_1
       3: ifeq          25
       6: iload_3
       7: iconst_0
       8: if_icmpne     12
      11: return
      12: aload_2
      13: invokeinterface #73,  1           // InterfaceMethod scala/Function0.apply:()Ljava/lang/Object;
      18: pop
      19: iinc          3, -1
      22: goto          2
      25: return

  public <A> A preventOptimization(A);
    Code:
       0: aload_1
       1: areturn

  public <A> void recurse(int, scala.Function0<A>);
    Code:
       0: iload_1
       1: iconst_0
       2: if_icmpne     6
       5: return
       6: aload_2
       7: invokeinterface #73,  1           // InterfaceMethod scala/Function0.apply:()Ljava/lang/Object;
      12: pop
      13: aload_0
      14: aload_0
      15: iload_1
      16: iconst_1
      17: isub
      18: aload_2
      19: invokevirtual #65                 // Method recurse:(ILscala/Function0;)V
      22: getstatic     #87                 // Field scala/runtime/BoxedUnit.UNIT:Lscala/runtime/BoxedUnit;
      25: invokevirtual #89                 // Method preventOptimization:(Ljava/lang/Object;)Ljava/lang/Object;
      28: pop
      29: return

  public int function2();
    Code:
       0: iconst_3
       1: ireturn

  private static final int main$$anonfun$1();
    Code:
       0: getstatic     #23                 // Field MODULE$:LMain$;
       3: invokevirtual #92                 // Method function2:()I
       6: ireturn

  private static final int main$$anonfun$2();
    Code:
       0: getstatic     #23                 // Field MODULE$:LMain$;
       3: invokevirtual #92                 // Method function2:()I
       6: ireturn

  private static java.lang.Object $deserializeLambda$(java.lang.invoke.SerializedLambda);
    Code:
       0: aload_0
       1: invokedynamic #104,  0            // InvokeDynamic #2:lambdaDeserialize:(Ljava/lang/invoke/SerializedLambda;)Ljava/lang/Object;
       6: areturn
}
```