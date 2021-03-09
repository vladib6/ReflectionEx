import org.jetbrains.annotations.NotNull;
import reflection.api.Investigator;

import java.lang.reflect.*;
import java.util.*;

public class Invs implements Investigator {
        //TODO: Check exceptions in all methods and dont print exceptions

        private Object obj;
        public Invs(){
        }


        @Override
        public void load(Object anInstanceOfSomething) {
            this.obj = anInstanceOfSomething;
        }

        @Override
        public int getTotalNumberOfMethods() {
           Method[] methods= obj.getClass().getDeclaredMethods();
            return methods.length;
        }

        @Override
        public int getTotalNumberOfConstructors() {
            Constructor[] constructors=obj.getClass().getDeclaredConstructors();
            return constructors.length;
        }

        @Override
        public int getTotalNumberOfFields() {
            Field[] fields=obj.getClass().getDeclaredFields();
            return fields.length;
        }

        @Override
        public Set<String> getAllImplementedInterfaces() {
            Set<String>  res=new HashSet<>();
               Class<?>[] interfaces= obj.getClass().getInterfaces();
               for(Class<?> cls:interfaces){
                   res.add(cls.getSimpleName());
               }

               return res;
         }

        @Override
        public int getCountOfConstantFields() {
            int count=0;
            Field[] fields=obj.getClass().getDeclaredFields();
            for(Field field: fields){
                if(Modifier.isFinal(field.getModifiers())){
                    count++;
                }
            }
            return count;
        }

        @Override
        public int getCountOfStaticMethods() {
           int count=0;
           Method[] methods=obj.getClass().getDeclaredMethods();
           for(Method method:methods){
               if(Modifier.isStatic(method.getModifiers())){
                   count++;
               }
           }
            return count;
        }

        @Override
        public boolean isExtending() {
             Class<?> res=obj.getClass().getSuperclass();

             if(res.equals(Object.class)){
                 return false;
             }else{
                 return true;
             }
        }

        @Override
        public String getParentClassSimpleName() {
            if(isExtending()){
                 return obj.getClass().getSuperclass().getSimpleName();
            }else{
                return null;
            }
        }

        @Override
        public boolean isParentClassAbstract() {
            if(isExtending()){
                if(Modifier.isAbstract(obj.getClass().getSuperclass().getModifiers())){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }

        @Override
      public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
            Set<String> res=new HashSet<>();
            Class<?> clazz=obj.getClass();
            do {
                Field[]fields=clazz.getDeclaredFields();
                for(Field field:fields){
                    res.add(field.getName());
                }
                clazz=clazz.getSuperclass();
            }while (!(clazz.equals(Object.class)));

            return res;
        }

        @Override
        public int invokeMethodThatReturnsInt(String methodName, Object... args) {
                Method[] methods = obj.getClass().getDeclaredMethods();
                int res=0;
                 for(Method method:methods){
                    if(method.getName().equals(methodName)){
                        try {
                            res=(int)method.invoke(obj,args);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                 return res;
        }


        @Override
        public Object createInstance(int numberOfArgs, Object... args) {
            Constructor[] constructors=obj.getClass().getConstructors();
            Object res = null;

            for(Constructor constructor:constructors){
                if(constructor.getParameterCount()==numberOfArgs){
                    try {
                        res=constructor.newInstance(args);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            return res;

        }

        @Override
        public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {//TODO
            Method[] methods=obj.getClass().getDeclaredMethods();
            Method methodInvoke = null;
            Object res=null;
            for(Method method:methods){
                if(method.getName().equals(name)){
                    methodInvoke=method;
                    break;
                }
            }
            methodInvoke.setAccessible(true);

            try {
                res=methodInvoke.invoke(obj,args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            return  res;
        }

        @Override
        public String getInheritanceChain(String delimiter) {
            String res=obj.getClass().getSimpleName();
            Class<?> superclass=obj.getClass().getSuperclass();
            while(superclass!=null) {
                res = superclass.getSimpleName()+delimiter+res;
                superclass=superclass.getSuperclass();
            }
            return res;
        }
    }

