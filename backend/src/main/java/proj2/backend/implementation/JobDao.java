package proj2.backend.implementation;



import java.util.List;

import proj2.backend.model.Job;

public interface JobDao {
void postJob(Job job);
List<Job> getAllJobs();
Job getJobDetail(int jobId);
}