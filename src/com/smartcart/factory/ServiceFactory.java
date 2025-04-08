package com.smartcart.factory;

import com.smartcart.annotations.Service;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactory {
    private static final Map<Class<?>, Object> services = new ConcurrentHashMap<>();

    public static <T> T getService(Class<T> serviceInterface) {
        return serviceInterface.cast(services.computeIfAbsent(serviceInterface, ServiceFactory::createInstance));
    }

    private static <T> T createInstance(Class<T> serviceInterface) {
        try {
            // Construct the expected implementation class name dynamically
            String implClassName = serviceInterface.getPackageName().replace(".interfaces", ".impl")
                    + "." + serviceInterface.getSimpleName().replace("Interface", "") + "Impl";

            System.out.println("Trying to load implementation: " + implClassName); // Debug log

            Class<?> implClass = Class.forName(implClassName);

            // Check if class is annotated with @Service
            if (!implClass.isAnnotationPresent(Service.class)) {
                throw new RuntimeException("Implementation class " + implClassName + " is missing @Service annotation.");
            }

            Constructor<?> constructor = implClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return serviceInterface.cast(constructor.newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create service for: " + serviceInterface.getName(), e);
        }
    }
}
