# breakable-if

A Clojure library which is a proof of concept for a flow of control construct
that works like clojure.core/if but which permits one to break out of the if.

You should structure your code differently instead of using this proof of concept.

One thing that works particularly well with if is that it returns a value.  iffy
will do the same.  In the library's current form, iffy returns nil if you break
which may not be what you want.  It's trivial to extend iffy-break to specify an
iffy return value.

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
```

## License

It's free, no copyright.  Take it if you want.  I accept no liability if you do.