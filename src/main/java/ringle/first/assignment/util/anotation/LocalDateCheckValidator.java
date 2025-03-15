package ringle.first.assignment.util.anotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerErrorException;
import ringle.first.assignment.util.exception.CustomException;
import ringle.first.assignment.util.exception.ErrorCodeType;

import java.lang.reflect.Field;
import java.time.LocalDate;

@Slf4j
public class LocalDateCheckValidator implements ConstraintValidator<LocalDateCheck, Object> {
    private String startDate;
    private String endDate;

    @Override
    public void initialize(LocalDateCheck constraintAnnotation) {
        startDate = constraintAnnotation.startDate();
        endDate = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        LocalDate lectureStart = getFieldValue(o, startDate);
        LocalDate lectureEnd = getFieldValue(o, endDate);
        if(!lectureEnd.isAfter(lectureStart)) {
            throw new CustomException(ErrorCodeType.TIME_REQUEST_ERROR_TWO);
        }
        return true;
    }

    private LocalDate getFieldValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        Field dataField;
        try {
            dataField = clazz.getDeclaredField(fieldName);
            dataField.setAccessible(true);
            Object target = dataField.get(object);
            if(!(target instanceof LocalDate)) {
                throw new ClassCastException("casting Exception");
            }
            return (LocalDate) target;
        } catch (NoSuchFieldException e) {
            log.error("NoSuchFieldException : ", e);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException : ", e);
        }
        throw new ServerErrorException("Not Found Field", new Throwable());
    }
}
