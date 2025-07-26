Log Monitoring Application

The application reads a log file and measures how long each job takes from start to finish. It also generates a warnings 
or errors if the processing time exceeds certain thresholds (5 minutes for warning and 10 minutes for error).

The application is able to do the following:
    1. Parse the CSV log file.
    2. Identify each job or task and track its start and finish times.
    3. Calculate the duration of each job from the time it started to the time it finished.
    4. Produce a report or output that:
       • Logs a warning if a job took longer than 5 minutes.
       • Logs an error if a job took longer than 10 minutes.

The logs within the log file have the following structure:
    • HH:MM:SS is a timestamp in hours, minutes, and seconds.
    • The job description.
    • Each log entry is either the “START” or “END” of a process.
    • Each job has a PID associated with it e.g., 46578.

The application can be started by running the Main class.

## Assumption Made
## I am assuming I will not have to do a continuous process, therefore if I find any log entry which has a start 
## but no end I will log it as an error.

