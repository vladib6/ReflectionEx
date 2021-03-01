import reflection.api.Investigator;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

public class Invs implements Investigator {


        private Class cls;
        public Invs(){
            cls=null;
        }

        public Class GetClass(){
            return cls;
        }

        @Override
        public void load(Object anInstanceOfSomething) {
            this.cls = anInstanceOfSomething.getClass();
        }

        @Override
        public int getTotalNumberOfMethods() {
           Method[] methods= cls.getDeclaredMethods();
            return methods.length;
        }

        @Override
        public int getTotalNumberOfConstructors() {
            Constructor[] constructors=cls.getDeclaredConstructors();
            return constructors.length;
        }

        @Override
        public int getTotalNumberOfFields() {
            Field[] fields=cls.getDeclaredFields();
            return fields.length;
        }

        @Override
        public Set<String> getAllImplementedInterfaces() {
            Set<String>  res=new HashSet<>();
               Class<?>[] interfaces= cls.getInterfaces();
               for(Class<?> cls:interfaces){
                   res.add(cls.getName());
               }

               return res;
         }

        @Override
        public int getCountOfConstantFields() {
            int count=0;
            Field[] fields=cls.getDeclaredFields();
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
           Method[] methods=cls.getDeclaredMethods();
           for(Method method:methods){
               if(Modifier.isStatic(method.getModifiers())){
                   count++;
               }
           }
            return count;
        }

        @Override
        public boolean isExtending() {
             Class<?> res=cls.getSuperclass();

             if(res.equals(Object.class)){
                 return false;
             }else{
                 return true;
             }
        }

        @Override
        public String getParentClassSimpleName() {
            if(isExtending()){
                 return cls.getSuperclass().getName();
            }else{
                return null;
            }
        }

        @Override
        public boolean isParentClassAbstract() {//TODO
            return false;
        }

        @Override
        public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {//TODO
            return null;
        }

        @Override
        public int invokeMethodThatReturnsInt(String methodName, Object... args) {//TODO
            return 0;
        }

        @Override
        public Object createInstance(int numberOfArgs, Object... args) {//TODO
            return null;
        }

        @Override
        public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {//TODO
            return null;
        }

        @Override
        public String getInheritanceChain(String delimiter) {//TODO
            return null;
        }
    }

