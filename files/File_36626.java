package command.later.good;

import command.later.good.command.Command;
import command.later.good.command.NoCommand;
import java.util.Stack;

//控制类
public class Control {
    private Command[] onCommands; //一列的 开�?�按钮
    private Command[] offCommands; //一列 关闭按钮
    private final int slotNum = 10;

    //为了实现undo�?作
    private Stack<Command> stack = new Stack<>();

    public Control() {
        //�?始化
        offCommands = new Command[slotNum]; //10排
        onCommands = new Command[slotNum];

        //下�?�就是NoCommand的作用, 并�?是�?一个按钮都对应�?�家电，有�?�能是空的,这样下�?�就�?�?判断是�?是空了
        Command noCommand = new NoCommand();
        for (int i = 0; i < onCommands.length; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    //�?�控器并�?知�?�绑定的是什么家具   解耦�?�
    //把命令对象设置到�?�控器上 : 很�?�? , 把命令�?装�?类 作为�?�数命令传进�?�，绑定到�?个�?�槽
    public void setOnCommand(int slot, Command onCommand) {
        onCommands[slot] = onCommand;
    }
    public void setOffCommand(int slot,Command offCommand) {
        offCommands[slot] = offCommand;
    }

    // 下�?�是三个控制器执行的方法
    public void on(int slot){
        onCommands[slot].execute();
        stack.push(onCommands[slot]);//记录
    }
    public void off(int slot){
        offCommands[slot].execute();
        stack.push(offCommands[slot]);
    }
    public void undo(){
        stack.pop().undo(); //具体的回退   �?回退的�?，首先�?记�?按了哪些按钮， �?�以使用栈的结构
    }
}
