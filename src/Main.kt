import kotlin.math.max

/**
 * EXERCÍCIO 1: SISTEMA DE FROTA ESTELAR
 * * Pilares a aplicar:
 * - Abstração: Classe base Nave.
 * - Encapsulamento: Controle de combustível e integridade.
 * - Herança: Especializações de naves.
 * - Polimorfismo: Comportamentos de viagem distintos.
 */

abstract class Nave(val nome: String, capacidadeCombustivel: Double) {
    // TODO: Encapsulamento - Tornar privado e criar getters/setters seguros
    protected var combustivel: Double = capacidadeCombustivel
    protected var integridadeDoCasco: Int = 100
    protected var modoEmergencia: Boolean = false

    // TODO: Abstração - Definir o contrato para viajar
    abstract fun viajar(distancia: Double)

    // TODO: Lógica de Dano - Implementar como a integridade é afetada
    open fun sofrerDano(impacto: Int) {
        integridadeDoCasco = max(0, integridadeDoCasco - impacto)
        if (integridadeDoCasco < 20) {
            modoEmergencia = true
            println("ALERTA: $nome em Modo de Emergência!")
        }
    }

    fun reparar() {
        // TODO: Implementar lógica de reparo que desativa o modo de emergência
    }

    fun status() {
        println("Nave: $nome | Casco: $integridadeDoCasco% | Combustível: $combustivel | Emergência: $modoEmergencia")
    }
}

class NaveGuerra(nome: String) : Nave(nome, 500.0) {
    private var escudosAtivos: Boolean = true

    override fun viajar(distancia: Double) {
        // TODO: Polimorfismo - Implementar viagem consumindo combustível
        // Se em modo de emergência, o consumo é dobrado.
    }

    // TODO: Polimorfismo/Herança - Sobrescrever sofrerDano para considerar escudos
}

class NaveCientifica(nome: String) : Nave(nome, 300.0) {
    private var pontosDescoberta: Int = 0

    override fun viajar(distancia: Double) {
        // TODO: Polimorfismo - Viagem gera pontos de descoberta
        // Reduzir consumo de combustível se sensores estiverem calibrados
    }
}

fun main() {
    // TODO: Testar o sistema criando instâncias e simulando danos e viagens
}