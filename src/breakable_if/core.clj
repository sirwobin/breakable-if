(ns breakable-if.core)

(defmacro iffy
  "A dubious flow of control construct like clojure.core/if that one can break out of to deliberately
   continue execution after the construct."
  ([condition then-body]
   `(try
       (if ~condition ~then-body)
       (catch breakable_if.NonBreakableIfException e#)))  ; this exception is deliberately ignored.
  ([condition then-body else-body]
   `(try
      (if ~condition ~then-body ~else-body)
      (catch breakable_if.NonBreakableIfException e#))))  ; this exception is deliberately ignored.

(defn iffy-break []
  (throw (breakable_if.NonBreakableIfException. "You must only call iffy-break within an iffy body.")))