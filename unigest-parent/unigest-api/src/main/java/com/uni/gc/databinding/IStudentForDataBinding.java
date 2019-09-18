package com.uni.gc.databinding;

import com.uni.gc.StudentForDatabindingDTO;

public interface IStudentForDataBinding {
	StudentForDatabindingDTO searchStudentById(Integer studentId, String studentName, String studentRegisterDate);
}
