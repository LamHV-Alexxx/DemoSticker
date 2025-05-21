# System Patterns

## System Architecture
- The Memory Bank is organized as a set of Markdown files in the `memory-bank/` directory.
- Each file has a specific purpose and builds upon others in a clear hierarchy.

## Key Technical Decisions
- All context is stored in plain Markdown for maximum accessibility and portability.
- Documentation is updated as a first-class part of the development workflow.

## Design Patterns
- Hierarchical documentation structure (see projectbrief.md for hierarchy).
- Context-first workflow: always read before acting.

## Component Relationships
- Core files provide foundational context; additional files extend or clarify as needed.

## Critical Implementation Paths
- Initialization: Create all core files if missing.
- Update: Revise documentation after significant changes or on request.
- Read: Always consult documentation before starting work. 