package com.University.databaseAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.University.DTO.Schedule;
import com.University.util.DBConnection;
public class ScheduleDAO {
	public List<Schedule> viewSchedule(int sem){
		List<Schedule> schedList = new ArrayList<Schedule>();
		String query = "select s.day, s.start_time, s.end_time, s.course_code , s.location, u.name as professor from schedules s join courses c on s.course_code = c.course_code join professors p on p.p_id = c.professor_id join users u on p.p_id = u.user_id where c.semester = ? order by s.day,s.start_time;";
		
		try(Connection conn = DBConnection.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, sem);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{
				String day = rs.getString("day");
				String start_time = rs.getString("start_time");
				String end_time = rs.getString("end_time");
				String course_code = rs.getString("course_code");
				String location = rs.getString("location");
				String professor = rs.getString("professor");
				
				Schedule  schedule = new Schedule(day,start_time,end_time,course_code,location,professor);
				schedList.add(schedule);
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return schedList;
	}
}