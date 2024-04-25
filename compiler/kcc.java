/*
 * this is the main kcc compiler class
 * @author Benjamin Powell
 * @version 0.1
 * Assignment 5
 * CS322 - Compiler Construction
 * Spring 2024
 */

//generic imports 
import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.Trees;
import java.util.Scanner;

import lexparse.*;

public class kcc {

   
    
    public static int main(String[] args){

    //creating all the proper extensions of the lexer parsers and tokens    
    CharStream input;
    KnightCodeLexer lexer;
    commonTokenStream token;
    KnightCodeParser parser;


    //
    //System.out.println("please enter the test u want to run");

    //Scanner scan = new Scanner(System.in);

    //String fileName = scan.nextLine();

    

    
    //the try catch used to create the parse tree
    try{
        input = CharStreams.fromFileName(args[0]);
        lexer = new KnightCodeLexer(input);
        token = new CommonTokenStream(lexer);
        parser = new KnightCodeParser(token);

        ParseTree tree = parser.file();
        Trees.inspect(tree , parser);

    } // end of try
    catch(IOException e){
        System.out.println(e.getMessage());

    }//end of catch
// ran out of time wasnt able to finish the readers to tell when to use each method
    //try {

        //here i make a buffered reader and writer so it can read the test files and then use the proper asm method

        //i ran out of time wasnt able to get a reader to tell what method to run based on text from file
        //BufferedReader reader = new BufferedReader(new FileReader(fileName));

      
        
    //} catch (Exception e) {
      //  e.printStackTrace();
    //}//end of catch

        System.out.println("it worked");

        //the method that uses asm to print out a integer
        static void printInt(int inputInt) {

                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();

        }//end of methodvisit creation

        {
            MethodVisitor mv = cw.VisitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);

            mv.visitCode();
            mv.visitLdcInsn((Integer)inputInt);
            mv.visitVarInsn(Opcodes.ISTORE,1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv,visitEnd();
        }//end of printIntMethodvisitor

        cw.visitEnd();

        Byte[] b = cw.toByteArray();

        writeFile(b,"program1");

           
        }//end of printInt

        //the method used to print out as tring using asm
        static void printString(String inputString){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();

        }//end of methodvisit creation

        {
            MethodVisitor mv = cw.VisitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode(); 
            mv.visitCode();
            mv.visitLdcInsn((String)inputString);// here we create a string
            mv.visitVarInsn(Opcodes.ASTORE, 1);// then we store the string in ASTORE
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//we then prepare the printstream
            mv.visitVarInsn(Opcodes.ALOAD, 1); // load the before made string
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); //finally print out the string


            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();




        }//end of printingaString in asm code

        cw.visitEnd();

        Byte[] b = cw.toByteArray();

        writeFile(b,"program1");



        }//end of printString 
//asm method used to add two numbers
        static void addition(int Num1 , int Num2){

               ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();

        }//end of methodvisit creation

        {
            mv.visitCode();
            mv.visitLdcInsn((Integer)Num1);
            mv.visitVarInsn(Opcodes.ISTORE,1);
            mv.visitLdcInsn((Integer)Num2);
            mv.visitVarInsn(Opcodes.ISTORE,3);
            my.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitVarInsn(Opcodes.ILOAD,3);
            mv.visitInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE,5);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD,5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();





        }//end of addition in asm

        cw.visitEnd();

        Byte[] b = cw.toByteArray();

        writeFile(b,"program1");


        }//end of addition
        //asm method to subtract 2 numbers
        static void subtract(int Num1, int Num2){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();

        }//end of methodvisit creation

       


        {
        mv.visitCode();
        mv.visitLdcInsn((Integer)Num1);
        mv.visitVarInsn(Opcodes.ISTORE,1);
        mv.visitLdcInsn((Integer)Num2);
        mv.visitVarInsn(Opcodes.ISTORE,3);
        my.visitVarInsn(Opcodes.ILOAD,1);
        mv.visitVarInsn(Opcodes.ILOAD,3);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitVarInsn(Opcodes.ISTORE,5);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD,5);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0,0);
        mv.visitEnd();
        }//end of asm subtract

        cw.visitEnd();

        Byte[] b = cw.toByteArray();

        writeFile(b,"program1");



        }//end of subtract
        //asm method for multiplying 2 numebrs
        static void multi(int Num1, int Num2){
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();

        }//end of methodvisit creation

        {

            mv.visitCode();
            mv.visitLdcInsn((Integer)Num1);
            mv.visitVarInsn(Opcodes.ISTORE,1);
            mv.visitLdcInsn((Integer)Num2);
            mv.visitVarInsn(Opcodes.ISTORE,3);
            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitVarInsn(Opcodes.ILOAD,3);
            mv.visitInsn(Opcodes.IMUL);
            mv.visitVarInsn(Opcodes.ISTORE,5);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System","out","Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD,5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V",false);

            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }//end of asm multiplication


        cw.visitEnd();

        Byte[] b = cw.toByteArray();

        writeFile(b,"program1");


        }//end of multi
        //asm method to divide
        static void divide(int Num1, int Num2){
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
            {
                MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(1,1);
                mv.visitEnd();
    
            }//end of methodvisit creation

            {
                mv.visitCode();
                mv.visitLdcInsn((Integer)Num1);
                mv.visitVarInsn(Opcodes.ISTORE,1);
                mv.visitLdcInsn((Integer)Num2);
                mv.visitVarInsn(Opcodes.ISTORE,3);
                mv.visitVarInsn(Opcodes.ILOAD,1);
                mv.visitVarInsn(Opcodes.ILOAD,3);
                mv.visitInsn(Opcodes.IDIV);
                mv.visitVarInsn(Opcodes.ISTORE,5);
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.ILOAD,5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream","println","(I)V",false);


                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(0,0);
                mv.visitEnd();


            }//end of asm divide code
            cw.visitEnd();

        Byte[] b = cw.toByteArray();

        writeFile(b,"program1");
    



        }//end of divide
        //asm method to compare 2 values
        static void comp(int Num1, int Num2){
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
            {
                MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(1,1);
                mv.visitEnd();
    
            }//end of methodvisit creation

            {
                mv.visitCode();
                Label intcomp1 = new Label();
                Label intcomp2 = new Label();
                mv.visitLdcInsn((Integer)Num1);
                mv.visitVarInsn(Opcodes.ISTORE,1);
                mv.visitLdcInsn((Integer)Num2);
                mv.visitVarInsn(Opcodes.ISTORE,3);
                mv.visitVarInsn(Opcodes.ILOAD,1);
                mv.visitVarInsn(Opcodes.ILOAD,3);
                mv.visitJumpInsn(Opcodes.IF_CMPLE, intcomp1);
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.ILOAD,1);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V",false);
                mv.visitJumpInsn(Opcodes.GOTO, intcomp2);
                mv.visitLabel(intcomp1);
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes>ILOAD,3);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V",false);

                mv.visitLabel(intcomp2);
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(0,0);
                mv.visitEnd();

            }//end of asm comp

            cw.visitEnd();

            Byte[] b = cw.toByteArray();
    
            writeFile(b,"program1");
        


        }//end of comp
        //asm method for a user input scanner
        static void userIntInput(int IntInput){
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
            {
                MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(1,1);
                mv.visitEnd();
    
            }//end of methodvisit creation

            {
                mv.visitCode();
                mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
                mv.visitInsn(Opcodes.DUP);
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Scanner","<init>","(Ljava/io/InputStream;)V",false );
                mv.visitVarInsn(Opcodes.ASTORE,1);
                mv.visitVarInsn(Opcodes.ALOAD,1);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util.Scanner", "nextInt", "()I",false);
                mv.visitMethodInsn(Opcoddes.INVOKESTATIC,"java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;",false);
                mv.visitVarInsn(Opcodes.ASTORE,3);
                mv.visitFieldInsn(Opcodes.GETSTATIC, " java/lang/System","out","Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.ALOAD,3);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream","println","(Ljava/lang/Object;)V");



            }//end of asm input


            cw.visitEnd();

            Byte[] b = cw.toByteArray();
    
            writeFile(b,"program1");



        }//end of userIntInput
        // was unable to finish in time but was asm method to take a string scanner input
        static void userStringInput(String StringInput){
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cw.visit(Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object",null);  
            {
                MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",null,null);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(1,1);
                mv.visitEnd();

                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(0,0);
                mv.visitEnd();

    
            }//end of methodvisit creation

            


        }//end of userStringInput



 

    }//end of main
}//end of kcc
