package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #83b9de");

        Scene scene = new Scene(root);

        // Добавляем меню
        root.getChildren().add(configureMenu());

        // Получаем HBox с текстовым полем внутри
        HBox box = configureResultView();
        // Добавляем HBox в root-вершину
        root.getChildren().add(box);
        // Устанавливаем отступы для HBox
        VBox.setMargin(box, new Insets(20, 10, 2, 10));


        root.getChildren().add(configureButtons());

        primaryStage.setTitle("Калькулятор");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class MyButton extends Button {
        MyButton(String text) {
            super(text);
            this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }

    private GridPane configureButtons() {
        GridPane pane = new GridPane();

        // 1ый ряд
        pane.add(new MyButton("MC"), 5, 0);
        pane.add(new MyButton("MR"), 6, 0);
        pane.add(new MyButton("MS"), 7, 0);
        pane.add(new MyButton("M+"), 8, 0);
        pane.add(new MyButton("M-"), 9, 0);

        // 2ой ряд
        pane.add(new MyButton("Inv"), 1, 1);
        pane.add(new MyButton("Ln"), 2, 1);
        pane.add(new MyButton("("), 3, 1);
        pane.add(new MyButton(")"), 4, 1);
        pane.add(new MyButton("<-"), 5, 1);
        pane.add(new MyButton("CE"), 6, 1);
        pane.add(new MyButton("C"), 7, 1);
        pane.add(new MyButton("±"), 8, 1);
        pane.add(new MyButton("√"), 9, 1);

        // 3ий ряд
        pane.add(new MyButton("Int"), 0, 2);
        pane.add(new MyButton("sinh"), 1, 2);
        pane.add(new MyButton("sin"), 2, 2);
        pane.add(new MyButton("x^2"), 3, 2);
        pane.add(new MyButton("n!"), 4, 2);
        pane.add(new MyButton("7"), 5, 2);
        pane.add(new MyButton("8"), 6, 2);
        pane.add(new MyButton("9"), 7, 2);
        pane.add(new MyButton("/"), 8, 2);
        pane.add(new MyButton("%"), 9, 2);

        // 4ый ряд
        pane.add(new MyButton("dms"), 0, 3);
        pane.add(new MyButton("cosh"), 1, 3);
        pane.add(new MyButton("cos"), 2, 3);
        pane.add(new MyButton("x^y"), 3, 3);
        pane.add(new MyButton("y√x"), 4, 3);
        pane.add(new MyButton("4"), 5, 3);
        pane.add(new MyButton("5"), 6, 3);
        pane.add(new MyButton("6"), 7, 3);
        pane.add(new MyButton("*"), 8, 3);
        pane.add(new MyButton("1/х"), 9, 3);

        // 5ый ряд
        pane.add(new MyButton("π"), 0, 4);
        pane.add(new MyButton("tanh"), 1, 4);
        pane.add(new MyButton("tan"), 2, 4);
        pane.add(new MyButton("x^3"), 3, 4);
        pane.add(new MyButton("3√x"), 4, 4);
        pane.add(new MyButton("1"), 5, 4);
        pane.add(new MyButton("2"), 6, 4);
        pane.add(new MyButton("3"), 7, 4);
        pane.add(new MyButton("-"), 8, 4);
        pane.add(new MyButton("="), 9, 4, 1, 2);
        //pane.add(new MyButton("="), 4, 4, 1, 2);

        // 6ой ряд
        pane.add(new MyButton("F-E"), 0,  5);
        pane.add(new MyButton("Exp"), 1,  5);
        pane.add(new MyButton("Mod"), 2,  5);
        pane.add(new MyButton("Log"), 3,  5);
        pane.add(new MyButton("10^x"), 4, 5);
        pane.add(new MyButton("0"), 5, 5, 2, 1);
        pane.add(new MyButton(","), 7, 5);
        pane.add(new MyButton("+"), 8, 5);


        ToggleGroup group = new ToggleGroup();

        RadioButton gradus = new RadioButton("Градусы");
        RadioButton radian = new RadioButton("Радианы");
        RadioButton grads = new RadioButton("Грады");
        gradus.setToggleGroup(group);
        radian.setToggleGroup(group);
        grads.setToggleGroup(group);

        pane.add(gradus,0,0,2,1);
        pane.add(radian, 2,0,2,1);
        pane.add(grads,4,0);

        // Свойства колонок
        ColumnConstraints cc = new ColumnConstraints();
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().addAll(cc,cc,cc,cc,cc);

        pane.setStyle("-fx-padding: 2 10 4 10");
        pane.setHgap(6);
        pane.setVgap(6);
        return pane;
    }

    private HBox configureResultView() {

        // Создаем менеджер HBox, куда
        // мы поместим текстовое поле
        HBox box = new HBox();

        // Устанавливаем нужный стиль для менеджера
        box.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 0;" +
                "-fx-border-color: black;" +
                "-fx-padding: 25 2 6 30;" +
                "-fx-background-color: linear-gradient(to bottom," +
                " #d3eefb 0%,#f4f8f9 59%);");

        // Создаем текстовое поле
        Text text = new Text("0");
        text.setTextAlignment(TextAlignment.RIGHT);
        text.setFont(new Font(40));

        // Добавяем текстовое поле в менеджер HBox
        // устанавливаем выравнивание
        box.getChildren().add(text);
        box.setAlignment(Pos.BOTTOM_RIGHT);

        return box;
    }

    private MenuBar configureMenu() {
        MenuBar bar = new MenuBar();

        //Вид
        Menu view = new Menu("_Вид");

        //до первого разделения
        RadioMenuItem casualItem = new RadioMenuItem("Обычный");
        casualItem.setAccelerator(KeyCombination.keyCombination("Alt+1"));
        RadioMenuItem engeeItem = new RadioMenuItem("Инженерный");
        engeeItem.setAccelerator(KeyCombination.keyCombination("Alt+2"));
        RadioMenuItem progrItem = new RadioMenuItem("Программист");
        progrItem.setAccelerator(KeyCombination.keyCombination("Alt+3"));
        RadioMenuItem statisItem = new RadioMenuItem("Статистика");
        statisItem.setAccelerator(KeyCombination.keyCombination("Alt+4"));
        ToggleGroup group1 = new ToggleGroup();
        //группирование
        casualItem.setToggleGroup(group1);
        engeeItem.setToggleGroup(group1);
        progrItem.setToggleGroup(group1);
        statisItem.setToggleGroup(group1);
        SeparatorMenuItem sep1 = new SeparatorMenuItem();

        CheckMenuItem journalItem = new CheckMenuItem("Журнал");
        journalItem.setAccelerator(KeyCombination.keyCombination("CTRL+H"));
        MenuItem groupItem = new MenuItem("Группировка цифр по разрядам");

        SeparatorMenuItem sep2 = new SeparatorMenuItem();

        RadioMenuItem casualItem1 = new RadioMenuItem("Обычный");
        casualItem1.setAccelerator(KeyCombination.keyCombination("CTRL+F4"));
        RadioMenuItem preobItem = new RadioMenuItem("Преобразование единиц");
        preobItem.setAccelerator(KeyCombination.keyCombination("CTRL+U"));
        RadioMenuItem vichislItem = new RadioMenuItem("Вычисление даты");
        vichislItem.setAccelerator(KeyCombination.keyCombination("CTRL+E"));

        ToggleGroup group2 = new ToggleGroup();
        casualItem1.setToggleGroup(group2);
        preobItem.setToggleGroup(group2);
        vichislItem.setToggleGroup(group2);

        Menu listsMenu = new Menu("Листы");
        MenuItem ipotekaItem = new MenuItem("Ипотека");
        MenuItem autolizItem = new MenuItem("Автолизинг");
        MenuItem econommilItem = new MenuItem("Экономия топлива (миль на галлон)");
        MenuItem economkmItem = new MenuItem("Экономия топлива (л/100 км)");
        listsMenu.getItems().addAll(ipotekaItem, autolizItem, econommilItem, economkmItem);


        view.getItems().addAll(casualItem, engeeItem, progrItem, statisItem,
                sep1, journalItem, groupItem, sep2, casualItem1, preobItem,
                vichislItem, listsMenu);

        //Правка
        Menu edit = new Menu("_Правка");
        MenuItem copy = new MenuItem("Копировать");
        copy.setAccelerator(KeyCombination.keyCombination("CTRL+C"));
        MenuItem paste = new MenuItem("Вставить");
        paste.setAccelerator(KeyCombination.keyCombination("CTRL+V"));

        SeparatorMenuItem sepEdit = new SeparatorMenuItem();

        Menu journalMenu = new Menu("Журнал");
        MenuItem copyjItem = new MenuItem("Копировать журнал");
        MenuItem changeItem = new MenuItem("Изменить");
        changeItem.setAccelerator(KeyCombination.keyCombination("F2"));
        MenuItem rejectItem = new MenuItem("Отменить правку");
        rejectItem.setAccelerator(KeyCombination.keyCombination("ESC"));
        MenuItem clearItem = new MenuItem("Очистить");
        clearItem.setAccelerator(KeyCombination.keyCombination("CTRL+SHIFT+D"));
        journalMenu.getItems().addAll(copyjItem, changeItem, rejectItem,clearItem);


        edit.getItems().addAll(copy,paste, sepEdit, journalMenu);

        //Справка
        Menu help = new Menu("_Справка");
        MenuItem show = new MenuItem("Посмотреть справку");
        show.setAccelerator(KeyCombination.keyCombination("F1"));
        SeparatorMenuItem sep3 = new SeparatorMenuItem();
        MenuItem about = new MenuItem("О программе");
        help.getItems().addAll(show,sep3,about);


        bar.getMenus().addAll(view, edit, help);

        return bar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
