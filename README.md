# Projeto Blackship

## Identificação

Curso: **LEI**

| Nome           | Número |
|----------------|--------|
| Santiago Alves | 129861 |
| Miguel Matos   | 129886 |
| Miguel Santos  | 125321 |

## Test suites individuais

### TestSuite_125321

Histórias testadas pelo membro 125321:

- **US-07** - Escolher o modo no ecra inicial.
- **US-36** - Jogar no navegador, em computador e telemovel.
- **US-37** - Aplicacoes Android e iOS.
- **US-41** - Acesso a politica de privacidade e termos.

Os cenários Selenium encontram-se em `src/test/java`, seguindo o padrão Page Object
Model. A suite Selenide usa Page Factory e Allure e encontra-se no pacote
`iscteiul.ista.blackbattleship.selenide`. Os cenários para importação no Selenium IDE
estão em `TestSuite_125321.side`.

| História | Verificação automatizada |
|----------|--------------------------|
| US-07 | Os quatro modos de jogo estão visíveis no ecrã, sem deslocamento, em computador e telemóvel. |
| US-36 | A página principal é apresentada em 390 × 844 px e não excede a largura do ecrã. |
| US-37 | As ligações para Google Play e App Store estão visíveis. |
| US-41 | As ligações para a política de privacidade e os termos estão visíveis. |

## Executar os testes

Requisitos: JDK 23 ou superior compatível com o nível de compilação definido no
Maven, Google Chrome instalado e ligação à Internet.

Na raiz do projeto, executar:

```powershell
.\mvnw.cmd clean test
```

Para executar apenas os testes Selenide:

```powershell
.\mvnw.cmd test -Dtest=BattleshipModeTest
```

Os resultados Allure são escritos em `target/allure-results`. Para gerar o
relatório configurado no projeto:

```powershell
.\mvnw.cmd allure:report
```

O relatório HTML incluído na entrega está em
`reports/TestSuite_125321_20261127-1551/`.
