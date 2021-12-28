# breakable-if

A Clojure library which is a proof of concept for a flow of control construct
that works like clojure.core/if but which permits one to break out of the if.

You should structure your code differently instead of using this proof of concept.
This is a bad idea.

One thing that works particularly well with if is that it returns a value and iffy
will do the same.  The final value of a then-body or else-body will normally be the
return value of iffy.  If you call iffy-break without a value then iffy will return
nil.  If you call iffy-break with a value, that value will become the value returned
by iffy.

This should allow functions inside the then-body or else-body also to interrupt the
flow of control in a calling function which I find very disturbing.  This unwholesome
feature would make tracing through code and deeply understanding it very complex.  

## Usage

```clojure
(require '[breakable-if.core :refer :all])

(iffy true 1 0)
=> 1

(iffy false 1 0)
=> 0

(iffy true
      (do
        (println "before")
        (iffy-break)
        (println "woo")
        3)
      5)
before
=> nil

(iffy true
      (do
        (println "before")
        (iffy-break 99)
        (println "woo")
        3)
      5)
before
=> 99
```

## License

It's free, no copyright.  Take it if you want.  I accept no liability if you do.