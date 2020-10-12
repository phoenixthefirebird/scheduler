**CPEN 221 / Fall 2020 / Lab 5**

ADTs: Working with `interface`s
===

### Interfaces

> Required Reading: **Abstract Data Types**

Task schedulers are common and they are found in many flavours. Schedulers differ in the manner in which they prioritize tasks. You have been given an `Scheduler` interface that will be used to schedule `Task`s. The tasks can be prioritized using different properties, and this leads to different scheduler implementations.

Look at the source code in `src/main/java/schedulers`.

You should implement three different schedulers as follows:

|Name|Policy Description|
|---:|---|
| **SRPT** | Shortest Remaining Processing Time: This scheduler prioritizes tasks that have the shortest remaining processing time. |
| **EDF** | Earliest Deadline First: This scheduler prioritizes tasks that have the earliest absolute deadlines. |
| **DM** | Deadline Monotonic: This scheduler prioritizes tasks on the basis of their _relative deadlines_: the shorter the relative deadline the higher the priority. |

These scheduling policies are often adopted by schedulers in operating systems or web servers depending on the needs of applications.

Your implementation should correspond to the provided interface: in Java terminology, you should create three classes that `implement` the `Scheduler` interface.
