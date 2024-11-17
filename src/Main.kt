import kotlin.math.abs

// Класс Точка
data class Point(val x: Double, val y: Double)

// Класс Треугольник
class Triangle(val p1: Point, val p2: Point, val p3: Point) {

    // Метод для вычисления площади треугольника по координатам вершин
    private fun area(p1: Point, p2: Point, p3: Point): Double {
        return abs((p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)) / 2.0)
    }

    // Метод для проверки, находится ли точка внутри треугольника
    fun isInside(point: Point): Boolean {
        val totalArea = area(p1, p2, p3)
        val area1 = area(point, p2, p3)
        val area2 = area(p1, point, p3)
        val area3 = area(p1, p2, point)

        // Сравниваем сумму площадей маленьких треугольников с площадью исходного треугольника
        return totalArea == (area1 + area2 + area3)
    }
}

// Функция для безопасного ввода координат
fun getDoubleInput(prompt: String): Double {
    while (true) {
        print(prompt)
        val input = readLine()
        try {
            return input?.toDouble() ?: throw IllegalArgumentException("Некорректный ввод")
        } catch (e: NumberFormatException) {
            println("Ошибка: введите числовое значение.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun main() {
    // Ввод координат вершин треугольника
    println("Введите координаты треугольника:")
    val x1 = getDoubleInput("x1: ")
    val y1 = getDoubleInput("y1: ")
    val x2 = getDoubleInput("x2: ")
    val y2 = getDoubleInput("y2: ")
    val x3 = getDoubleInput("x3: ")
    val y3 = getDoubleInput("y3: ")

    val triangle = Triangle(Point(x1, y1), Point(x2, y2), Point(x3, y3))

    // Ввод координат точки
    println("Введите координаты точки:")
    val x = getDoubleInput("x: ")
    val y = getDoubleInput("y: ")

    val point = Point(x, y)

    // Проверка положения точки относительно треугольника
    if (triangle.isInside(point)) {
        println("Точка находится внутри треугольника.")
    } else {
        println("Точка находится вне треугольника.")
    }
}