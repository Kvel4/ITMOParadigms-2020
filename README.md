</td></tr></table><table id="body"><tr><td id="main"><h3 id="homework-1">Домашнее задание 1. Обработка ошибок</h3><ol><li>
            Добавьте в программу вычисляющую выражения обработку ошибок, в том числе:
            <ul><li>ошибки разбора выражений;</li><li>ошибки вычисления выражений.</li></ul></li><li>
            Для выражения <code>1000000*x*x*x*x*x/(x-1)</code> вывод программы
            должен иметь следующий вид:
            <pre>
x       f
0       0
1       division by zero
2       32000000
3       121500000
4       341333333
5       overflow
6       overflow
7       overflow
8       overflow
9       overflow
10      overflow
            </pre>
            Результат <code>division by zero</code> (<code>overflow</code>) означает,
            что в процессе вычисления произошло деление на ноль (переполнение).
        </li><li>
            При выполнении задания следует обратить внимание на дизайн и обработку исключений.
        </li><li>
            Человеко-читаемые сообщения об ошибках должны выводится на консоль.
        </li><li>
            Программа не должна &laquo;вылетать&raquo; с исключениями (как стандартными, так и добавленными).
        </li></ol><p><a href="https://www.kgeorgiy.info/git/geo/paradigms-2020">Тесты к домашним заданиям</a></p><h3 id="homework-2">Домашнее задание 2. Бинарный поиск</h3><ol><li>
            Реализуйте итеративный и рекурсивный варианты бинарного поиска в массиве.
        </li><li>
            На вход подается целое число <code>x</code> и массив целых чисел <code>a</code>,
            отсортированный по невозрастанию.
            Требуется найти минимальное значение индекса <code>i</code>,
            при котором <code>a[i] &lt;= x</code>.
        </li><li>
            Для функций бинарного поиска и вспомогательных функций должны быть указаны,
            пред- и постусловия. Для реализаций методов должны быть приведены
            доказательства соблюдения контрактов в терминах троек Хоара.
        </li><li>
            Интерфейс программы.
            <ul><li>Имя основного класса &mdash; <code>BinarySearch</code>.</li><li>Первый аргумент командной строки &mdash; число <code>x</code>.</li><li>Последующие аргументы командной строки &mdash; элементы массива <code>a</code>.</li></ul></li><li>
            Пример запуска: <code>java BinarySearch 3 5 4 3 2 1</code>.
            Ожидаемый результат: <code>2</code>.
        </li></ol><h3 id="homework-3">Домашнее задание 3. Очередь на массиве</h3><ol><li>
            Найдите инвариант структуры данных &laquo;<a href="http://ru.wikipedia.org/wiki/%D0%9E%D1%87%D0%B5%D1%80%D0%B5%D0%B4%D1%8C_(%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5)">очередь</a>&raquo;.
            Определите функции, которые необходимы для реализации очереди.
            Найдите их пред- и постусловия, при условии что очередь не содержит <code>null</code>.
        </li><li>
            Реализуйте классы, представляющие циклическую очередь с применением массива.
            <ul><li>
                    Класс <code>ArrayQueueModule</code> должен реализовывать один экземпляр
                    очереди с использованием переменных класса.
                </li><li>
                    Класс <code>ArrayQueueADT</code> должен реализовывать очередь в виде
                    абстрактного типа данных (с явной передачей ссылки на экземпляр очереди).
                </li><li>
                    Класс <code>ArrayQueue</code> должен реализовывать очередь в виде
                    класса (с неявной передачей ссылки на экземпляр очереди).
                </li><li>
                    Должны быть реализованы следующие функции (процедуры) / методы:
                    <ul><li><code>enqueue</code> &ndash; добавить элемент в очередь;</li><li><code>element</code> &ndash; первый элемент в очереди;</li><li><code>dequeue</code> &ndash; удалить и вернуть первый элемент в очереди;</li><li><code>size</code> &ndash; текущий размер очереди;</li><li><code>isEmpty</code> &ndash; является ли очередь пустой;</li><li><code>clear</code> &ndash; удалить все элементы из очереди.</li></ul></li><li>
                    Инвариант, пред- и постусловия записываются в исходном коде в виде
                    комментариев.
                </li><li>
                    Обратите внимание на инкапсуляцию данных и кода во всех трех реализациях.
                </li></ul></li><li>
            Напишите тесты к реализованным классам.
        </li></ol><h3 id="homework-4">Домашнее задание 4. Очереди</h3><ol><li>
            Определите интерфейс очереди <code>Queue</code> и опишите его контракт.
        </li><li>
            Реализуйте класс <code>LinkedQueue</code> &mdash; очередь на связном списке.
        </li><li>
            Выделите общие части классов <code>LinkedQueue</code>
            и <code>ArrayQueue</code> в базовый класс <code>AbstractQueue</code>.
        </li></ol><h3 id="homework-5">Домашнее задание 5. Вычисление в различных типах</h3><ol><li>
            Добавьте в программу разбирающую и вычисляющую выражения поддержку различных типов.
            <ul><li>
                    Первым аргументом командной строки программа должна принимать указание
                    на тип, в котором будут производится вычисления:
                    <table><tr><th>Опция</th><th>Тип</th></tr><tr><td><code>-i</code></td><td><code>int</code></td></tr><tr><td><code>-d</code></td><td><code>double</code></td></tr><tr><td><code>-bi</code></td><td><code><a href="http://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html">BigInteger</a></code></td></tr></table></li><li>
                    Вторым аргументом командной строки программа должна принимать
                    выражение для вычисления.
                </li><li>
                    Реализация не должна содержать
                    <a href="http://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html#jls-5.1.9">непроверяемых преобразований типов</a>.
                </li><li>
                    Реализация не должна использовать аннотацию
                    <code><a href="http://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.6.4.5">@SuppressWarnings</a></code>.
                </li></ul></li><li>
            При выполнении задания следует обратить внимание на легкость добавления новых типов и операциий.
        </li></ol><h3 id="homework-6">Домашнее задание 6. Функциональные выражения на JavaScript</h3><ol><li>
            Разработайте функции <code>cnst</code>, <code>variable</code>,
            <code>add</code>, <code>subtract</code>, <code>multiply</code>,
            <code>divide</code>, <code>negate</code>
            для вычисления выражений с одной переменной.
        </li><li>
            Функции должны позволять производить вычисления вида:
            <pre>
let expr = subtract(
    multiply(
        cnst(2),
        variable("x")
    ),
    cnst(3)
);
println(expr(5));
            </pre>
            При вычислении такого выражения вместо каждой переменной подставляется значение,
            переданное в качестве параметра функции <code>expr</code> (на данном этапе
            имена переменных игнорируются). Таким образом, результатом вычисления
            приведенного примера должно стать число 7.
        </li><li>
            Тестовая программа должна вычислять выражение
            <code>x<sup>2</sup>&minus;2x+1</code>, для <code>x</code> от 0 до 10.
        </li><li><b>Усложненный вариант.</b> Требуется написать функцию
            <code>parse</code>, осуществляющую разбор выражений,
            записанных в
            <a href="http://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C">обратной польской записи</a>.
            Например, результатом
            <pre>parse("x x 2 - * x * 1 +")(5)</pre>
            должно быть число <code>76</code>.
        </li><li>
            При выполнение задания следует обратить внимание на:
            <ul><li>
                    Применение функций высшего порядка.
                </li><li>
                    Выделение общего кода для бинарных операций.
                </li></ul></li>
            </li></ul></li></ol><h3 id="homework-7">Домашнее задание 7. Объектные выражения на JavaScript</h3><ol><li>
            Разработайте классы <code>Const</code>, <code>Variable</code>,
            <code>Add</code>, <code>Subtract</code>, <code>Multiply</code>,
            <code>Divide</code>, <code>Negate</code>
            для представления выражений с одной переменной.
            <ol><li>
                    Пример описания выражения <code>2x-3</code>:
                    <pre>
let expr = new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
);
                    </pre></li><li>
                    Метод <code>evaluate(x)</code> должен производить вычисления вида:
                    При вычислении такого выражения вместо каждой переменной подставляется значение <code>x</code>,
                    переданное в качестве параметра функции <code>evaluate</code> (на данном этапе
                    имена переменных игнорируются). Таким образом, результатом вычисления
                    приведенного примера должно стать число 7.
                </li><li>
                    Метод <code>toString()</code> должен выдавать
                    запись выражения в
                    <a href="http://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C">обратной польской записи</a>.
                    Например, <code>expr.toString()</code> должен выдавать
                    <code>2 x * 3 -</code>.
                </li></ol></li><li><b>Сложный вариант.</b><div>
            Метод <code>diff("x")</code> должен возвращать выражение,
            представляющее производную исходного выражения
            по переменной <code>x</code>.
            Например, <code>expr.diff("x")</code> должен возвращать
            выражение, эквивалентное <code>new Const(2)</code>
            (выражения
                <code>new Subtract(new Const(2), new Const(0))</code> и
                <pre>
new Subtract(
    new Add(
        new Multiply(new Const(0), new Variable("x")),
        new Multiply(new Const(2), new Const(1))
    )
    new Const(0)
)
                 </pre>
                 так же будут считаться правильным ответом).
            </div><p>
                Функция <code>parse</code> должна выдавать разобранное
                объектное выражение.
            </p></li><li><b>Бонусный вариант.</b>
            Требуется написать
            метод <code>simplify()</code>, производящий вычисления
            константных выражений. Например,
            <pre>parse("x x 2 - * 1 *").diff("x").simplify().toString()</pre>
            должно возвращать &laquo;<code>x x 2 - +</code>&raquo;.
        </li><li>
            При выполнение задания следует обратить внимание на:
            <ul><li>
                    Применение инкапсуляции.
                </li><li>
                    Выделение общего кода для операций.
                </li></ul></li></ol><h3 id="homework-8">Домашнее задание 8. Обработка ошибок на JavaScript</h3><ol><li>
            Добавьте в предыдущее домашнее задание функцию
            <code>parsePrefix(string)</code>, разбирающую выражения,
            задаваемые записью вида <code>(- (* 2 x) 3)</code>.
            Если разбираемое выражение некорректно, метод
            <code>parsePrefix</code> должен бросать
            человеко-читаемое сообщение об ошибке.
        </li><li>
            Добавьте в предыдущее домашнее задание метод
            <code>prefix()</code>, выдающий выражение в формате,
            ожидаемом функцией <code>parsePrefix</code>.
        </li><li>
            При выполнение задания следует обратить внимание на:
            <ul><li>
                    Применение инкапсуляции.
                </li><li>
                    Выделение общего кода для бинарных операций.
                </li><li>
                    Обработку ошибок.
                </li><li>
                    Минимизацию необходимой памяти.
                </li></ul></li></ol><h3 id="homework-9">Домашнее задание 9. Линейная алгебра на Clojure</h3><ol><li>
            Разработайте функции для работы с объектами линейной алгебры,
            которые представляются следующим образом:
            <ul><li>скаляры &ndash; числа</li><li>векторы &ndash; векторы чисел;</li><li>матрицы &ndash; векторы векторов чисел.</li></ul></li><li>
            Функции над векторами:
            <ul><li><code>v+</code>/<code>v-</code>/<code>v*</code>
                    &ndash; покоординатное сложение/вычитание/умножение;
                </li><li><code>scalar</code>/<code>vect</code>
                    &ndash; скалярное/векторное произведение;
                </li><li><code>v*s</code>
                    &ndash; умножение на скаляр.
                </li></ul></li><li>
            Функции над матрицами:
            <ul><li><code>m+</code>/<code>m-</code>/<code>m*</code>
                    &ndash; поэлементное сложение/вычитание/умножение;
                </li><li><code>m*s</code> &ndash; умножение на скаляр;
                </li><li><code>m*v</code> &ndash; умножение на вектор;
                </li><li><code>m*m</code> &ndash; матричное умножение;
                </li><li><code>transpose</code> &ndash; траспонирование;
                </li></ul></li><li><b>Сложный вариант.</b><ol><li>
                    Ко всем функциям должны быть указаны контракты.
                    Например, нельзя складывать вектора разной длины.
                </li><li>
                    Все функции должны поддерживать произвольное число аргументов.
                    Например
                    <code>(v+ [1 2] [3 4] [5 6])</code> должно быть равно
                    <code>[9 12]</code>.
                </li></ol></li><li>
            При выполнение задания следует обратить внимание на:
            <ul><li>
                    Применение функций высшего порядка.
                </li><li>
                    Выделение общего кода для операций.
                </li></ul></li>
              